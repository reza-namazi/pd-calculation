package ir.pd

import groovy.transform.Canonical
import groovy.transform.Immutable
import groovy.transform.ImmutableBase
import groovy.transform.builder.Builder

import java.time.Instant

@Immutable(copyWith = true)
@Canonical
@Builder
class Request {
    String correlationId
    Instant currentDate
    Integer manufactureYear
    Integer numberOfNoClaimYears
    BigDecimal numberOfNoClaimRate
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
