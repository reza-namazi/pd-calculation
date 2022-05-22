package ir.pd


import groovy.transform.ToString
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import ir.pd.calc.CalculationType

import java.util.concurrent.CopyOnWriteArrayList

@TupleConstructor
@ToString(includeNames = false, excludes = ['request'])
@Slf4j
class Calculation {

    Request request
    private def details = [] as CopyOnWriteArrayList<CalculationDetail>

    BigDecimal get(CalculationType ct) {
        details.find { it.ct == ct }?.get()
    }

    Calculation(Request request) {
        this.request = request
    }

    def eval() {
        log.error('eval called')
        details.collect { it.eval() }

        this
    }

    CalculationBuilder next(CalculationType ct) {
        new CalculationBuilder(new CalculationDetail(ct))
    }

    @TupleConstructor
    class CalculationBuilder {
        CalculationDetail cd

        CalculationBuilder with(Closure<BigDecimal> actualCalc) {
            cd.actualCalc = actualCalc
            this
        }

        CalculationBuilder detail(Closure<Map> detail) {
            cd.detail = detail
            this
        }

        Calculation end() {
            Calculation.this.details += cd
            Calculation.this
        }
    }

    @TupleConstructor
    class CalculationDetail {
        CalculationType ct
        BigDecimal value
        Closure<Map> detail
        Closure<BigDecimal> actualCalc

        BigDecimal get() {
            if (value == null) {
                eval()
            }
            value
        }

        def eval() {
            value = actualCalc(request, Calculation.this)
            log.debug("{} - {} = {}", Calculation.this.request.correlationId, ct, value)
            value
        }
    }
}
