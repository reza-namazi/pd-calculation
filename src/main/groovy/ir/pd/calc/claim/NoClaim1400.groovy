package ir.pd.calc.claim

import groovy.util.logging.Slf4j
import ir.pd.Calculation
import ir.pd.Request
import ir.pd.calc.CalcItem
import ir.pd.calc.CalculationType

import static ir.pd.calc.CalculationType.BASE
import static ir.pd.calc.CalculationType.NO_CLAIM

@Slf4j
class NoClaim1400 implements CalcItem {
    @Override
    CalculationType calculationTyp() {
        NO_CLAIM
    }

    /**
     true means this rule applies in any case
     you can add some other conditions like,
     if it's a motorcycle then this rule doesn't apply.
     * */
    @Override
    boolean match(Request request) {
        true
    }

    @Override
    Calculation apply(Calculation calculation) {
        calculation.next(NO_CLAIM)
                .with({ Request r, Calculation c -> r.numberOfNoClaimYears / 100 * 10 * c.get(BASE) + c.get(BASE) })
                .detail({ Request r, Calculation c -> [type: 'NO_CLAIM', base: c.get(BASE)] })
                .end()
    }
}
