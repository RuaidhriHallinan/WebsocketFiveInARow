package com.gen.fiveinarow.config;

import com.gen.fiveinarow.config.GameWebSocketConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameWebSocketConfigTest {

	/**
	 * Check that configureMessageBroker() creates a /topic broker
	 * and configures an /app destination prefix.
	 */
	@Test
	public void testConfigureMessageBrokerMakesExpectedCalls() {

		MessageBrokerRegistry registry = Mockito.mock(MessageBrokerRegistry.class);

		GameWebSocketConfig gameWebSocketConfig = new GameWebSocketConfig();
		gameWebSocketConfig.configureMessageBroker(registry);

		verify(registry, times(1)).enableSimpleBroker(anyString());
		verify(registry, times(1)).enableSimpleBroker("/topic");

		verify(registry, times(1)).setApplicationDestinationPrefixes(anyString());
		verify(registry, times(1)).setApplicationDestinationPrefixes("/app");
	}

	/**
	 * Check that the endpoint "fiveinarow-example" is registered, and
	 * that withSockJS() is called.
	 */
	@Test
	public void testRegisterCustomEndpointsMakesExpectedCalls() {

		StompWebSocketEndpointRegistration registration = Mockito.mock(StompWebSocketEndpointRegistration.class);
		StompEndpointRegistry registry = Mockito.mock(StompEndpointRegistry.class);

		when(registry.addEndpoint("/fiveinarow-example")).thenReturn(registration);

		GameWebSocketConfig gameWebSocketConfig = new GameWebSocketConfig();
		gameWebSocketConfig.registerStompEndpoints(registry);

		verify(registry, times(1)).addEndpoint(anyString());
		verify(registry, times(1)).addEndpoint("/fiveinarow-example");

		verify(registration, times(1)).withSockJS();
	}


}
