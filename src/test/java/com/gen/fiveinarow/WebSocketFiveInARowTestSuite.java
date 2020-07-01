package com.gen.fiveinarow;

import com.gen.fiveinarow.config.GameWebSocketConfigTest;
import com.gen.fiveinarow.controller.GameControllerIntegrationTest;
import com.gen.fiveinarow.controller.GameControllerUnitTest;
import com.gen.fiveinarow.controller.GameControllerStateLogicTest;
import com.gen.fiveinarow.game.GameStateTest;
import com.gen.fiveinarow.model.GameModelTest;
import com.gen.fiveinarow.model.PlayerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlayerTest.class, //test case 1
        GameModelTest.class, //test case 2
        GameStateTest.class, //test case 3
        GameControllerStateLogicTest.class, //test case 4
        GameControllerUnitTest.class, //test case 5
        GameWebSocketConfigTest.class, //test case 6
        GameControllerIntegrationTest.class, //test case 7
        WebsocketFiveInARowApplicationTest.class //test case 8
})

@SpringBootTest
public class WebSocketFiveInARowTestSuite {
}
