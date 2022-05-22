package ir.pd.calc

import ir.pd.Calculation
import ir.pd.Request
import ir.pd.UsageType
import ir.pd.VehicleBodyType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.assertThat

import java.time.Instant

class Default1400CalculatorTest {

    def static today = Instant.parse('2022-05-19T15:38:34.552Z')
    def calculator

    @BeforeEach
    void setUp() {
        calculator = new Default1400Calculator()
    }

    @Test
    void shouldCalculate() {
        //given
        def request = new Request('correlationId', today, 1400, 2, VehicleBodyType.SEDAN, UsageType.PERSONAL, 0.3)
        def calculation = new Calculation(request: request)

        //when
        def result = calculator.calculate(request, calculation)

        //then
        assertThat(result as String).isEqualTo("[100.0, 120.000, 226.600000, 446.600000]")

    }
}
