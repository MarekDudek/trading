package com.marekdudek.trading;

import org.junit.Test;

import javax.websocket.*;
import java.net.URI;

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
}
