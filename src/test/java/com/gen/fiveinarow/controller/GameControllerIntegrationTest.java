package com.gen.fiveinarow.controller;

import com.gen.fiveinarow.WebsocketFiveInARowApplication;
import com.gen.fiveinarow.config.ApplicationTestConfig;
import com.gen.fiveinarow.model.GameModel;
import com.gen.fiveinarow.model.Player;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {WebsocketFiveInARowApplication.class, ApplicationTestConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) //This cleans up and terminates the application after the test
public class GameControllerIntegrationTest {

    private static final String TOPIC_MESSAGES = "/topic/fiveinarow";
    private static final String GAME_SEND_MESSAGE = "/app/game";
    private static final String MOVE_SEND_MESSAGE = "/app/move";
    private static String URL;

    private int port = 8091;

    @Autowired
    private WebSocketStompClient stompClient;
    private CompletableFuture<GameModel> completableFuture = new CompletableFuture<>();
    private StompSession stompSession;

    @Before
    public void init() throws InterruptedException, ExecutionException, TimeoutException {
        URL = "ws://localhost:"+port+"/fiveinarow-example";
        stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {}).get(1, SECONDS);
        stompSession.subscribe(TOPIC_MESSAGES, new CreateStompFrameHandler());
    }

    @Test
    public void a_gameMessagePlayer1JoinBroadcastToListeners() throws InterruptedException, ExecutionException, TimeoutException {
        Player player = new Player("Jon Snow");
        stompSession.send(GAME_SEND_MESSAGE, player);

        GameModel messageResponse = completableFuture.get(10, SECONDS);
        assertThat(messageResponse.getMessage()).contains("Welcome to the game, " + player.getName());
    }

    @Test
    public void b_gameMessagePlayer2JoinBroadcastToListeners() throws InterruptedException, ExecutionException, TimeoutException {
        Player player = new Player("Frodo Baggins");
        stompSession.send(GAME_SEND_MESSAGE, player);

        GameModel messageResponse = completableFuture.get(10, SECONDS);
        assertThat(messageResponse.getMessage()).contains("Welcome to the game, " + player.getName());
    }

    @Test
    public void c_moveInvalidPlayer2MoveMessageBroadcastToListeners() throws InterruptedException, ExecutionException, TimeoutException {
        Player player = new Player("Frodo Baggins", 1);
        stompSession.send(MOVE_SEND_MESSAGE, player);

        GameModel messageResponse = completableFuture.get(10, SECONDS);
        assertThat(messageResponse.getMessage()).contains("Its player 1's turn!");
    }

    @Test
    public void d_moveInvalidPlayer1MessageBroadcastToListeners() throws InterruptedException, ExecutionException, TimeoutException {
        Player player = new Player("Jon Snow", 1);
        stompSession.send(MOVE_SEND_MESSAGE, player);

        GameModel messageResponse = completableFuture.get(10, SECONDS);
        assertThat(messageResponse.getMessage()).contains("Player 2's turn!");
    }

    private class CreateStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return GameModel.class;
        }
        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((GameModel) o);
        }
    }
}
