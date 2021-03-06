package ir.pd.calc.total

import groovy.util.logging.Slf4j
import ir.pd.Calculation
import ir.pd.Request
import ir.pd.calc.CalcItem
import ir.pd.calc.CalculationType

import static ir.pd.calc.CalculationType.*

@Slf4j
class DefaultTotal1400 implements CalcItem {

    @Override
    CalculationType calculationTyp() {
        TOTAL
    }

    @Override
    boolean match(Request request) {
        true
    }

    @Override
    Calculation apply(Calculation calculation) {
        calculation.next(TOTAL)
                .with { Request r, Calculation c -> c.get(BASE) + c.get(NO_CLAIM) + c.get(TAX) }
                .detail { Request r, Calculation c -> [type: 'TOTAL', base: c.get(BASE), noClaim: c.get(NO_CLAIM), tax: c.get(TAX)] }
                .end()
    }

}
