package com.marekdudek.trading;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public final class Platform {
    public final Integer status;
}
