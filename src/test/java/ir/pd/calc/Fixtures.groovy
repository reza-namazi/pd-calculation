package ir.pd.calc

import ir.pd.Calculation
import ir.pd.Request
import ir.pd.UsageType
import ir.pd.VehicleBodyType

import java.time.Instant

class Fixtures {

    def static request(Instant currentDate = Instant.parse('2022-05-19T15:38:34.552Z'),
                       int manufactureDate = 1400,
                       int numberOfNoClaimYears = 1,
                       VehicleBodyType bodyType = VehicleBodyType.SEDAN,
                       UsageType usageType = UsageType.PERSONAL,
                       BigDecimal tax = 0.2) {
        new Request(currentDate, manufactureDate, numberOfNoClaimYears, bodyType, usageType, tax)
    }

    def static calculation() {
        new Calculation()
    }
}
