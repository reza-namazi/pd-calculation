package ir.pd

import groovy.transform.Immutable
import groovy.transform.builder.Builder

import java.time.Instant

@Immutable
@Builder
class Request {
    String correlationId
    Instant currentDate
    int manufactureDate
    int numberOfNoClaimYears
    VehicleBodyType bodyType
    UsageType usageType
    BigDecimal tax
}

enum VehicleBodyType {
    SEDAN,
    PICKUP,
    VAN,
    MINI_TRUCK,
    TRUCK,
    BUS,
    MINI_BUS
}

enum UsageType {
    PERSONAL,
    TAXI,
    CARGO_CARRY
}
