package com.marekdudek.trading;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public final class InfoMessage {
    public final String event;
    public final String version;
    public final String serverId;
    public final Platform platform;
}
