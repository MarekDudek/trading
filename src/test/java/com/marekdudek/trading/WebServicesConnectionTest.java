package com.marekdudek.trading;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

import static java.lang.System.err;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ClientEndpoint
public final class WebServicesConnectionTest {

    private static final String BITFINEX_URI = "wss://api.bitfinex.com/ws/";

    @Test
    public void connection_can_be_opened() throws Exception {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        final URI uri = URI.create(BITFINEX_URI);
        final Session session = container.connectToServer(this, uri);
        assertThat(session.isOpen(), is(true));
        session.close();
    }

    private static final String SUBSCRIPTION = "{\"event\":\"subscribe\",\"channel\":\"trades\",\"pair\":\"BTCUSD\"}";

    @Test
    public void subscribed_to_topic() throws Exception {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        final URI uri = URI.create(BITFINEX_URI);
        final Session session = container.connectToServer(this, uri);
        final RemoteEndpoint.Basic remote = session.getBasicRemote();
        remote.sendText(SUBSCRIPTION);
        session.close();
    }

    @Test
    public void periodically_receive_messages() throws Exception {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        final URI uri = URI.create(BITFINEX_URI);
        final Session session = container.connectToServer(this, uri);
        final RemoteEndpoint.Basic remote = session.getBasicRemote();
        remote.sendText(SUBSCRIPTION);
        err.println("Sleeping ...");
        Thread.sleep(1_000);
        err.println("... done.");
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    @OnMessage
    public void onMessage(final Session session, final String message) throws IOException {
        final JsonNode node = objectMapper.readTree(message);
        err.format("%s%n", node);
    }
}
