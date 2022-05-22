package ir.pd.calc


import ir.pd.Request
import ir.pd.RequestHandler
import ir.pd.UsageType
import ir.pd.VehicleBodyType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.Instant

import static org.assertj.core.api.Assertions.assertThat

class Default1400CalculatorTest {

    def static today = Instant.parse('2022-05-19T15:38:34.552Z')
    def calculator
    def requestHandler

    @BeforeEach
    void setUp() {
        calculator = new Default1400Calculator()
        requestHandler = new RequestHandler()
    }

    @Test
    void shouldCalculate() {
        //given
        def request = new Request(
                correlationId: "",
                currentDate: Instant.now(),
                manufactureYear: 1400,
                numberOfNoClaimYears: 2,
                numberOfNoClaimRate: null,
                bodyType: VehicleBodyType.SEDAN,
                usageType: UsageType.PERSONAL,
                tax: null)

        //when
        def response = requestHandler.handle(request)

        //then
        assertThat(response.get().result as String).isEqualTo("[100.0, 120.000, 226.600000, 446.600000]")
    }
}
