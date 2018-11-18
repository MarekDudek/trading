package com.marekdudek.trading;

import org.junit.Test;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ClientEndpoint
public final class WebServicesConnectionTest {

    private static final String BITFINEX_URI = "wss://api.bitfinex.com/ws/";

    @Test
    public void connection_can_be_opened() throws IOException, DeploymentException {
        final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        final URI uri = URI.create(BITFINEX_URI);
        final Session session = container.connectToServer(this, uri);
        assertThat(session.isOpen(), is(true));
        session.close();
    }
}
