package com.marekdudek.trading;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public final class InfoMessageTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String Example =
            "{\"event\":\"info\",\"version\":1.1,\"serverId\":\"13e86f9e-6f6f-4842-9111-adc2dbdf8200\",\"platform\":{\"status\":1}}";

    @Test
    public void example_can_be_parsed() throws IOException {
        // when
        final InfoMessage message = MAPPER.readValue(Example, InfoMessage.class);
        // then
        assertThat(message,
                is(
                        InfoMessage.builder().
                                event("info").
                                version("1.1").
                                serverId("13e86f9e-6f6f-4842-9111-adc2dbdf8200").
                                platform(
                                        Platform.builder().status(1).build()
                                ).
                                build()
                )
        );
    }
}
