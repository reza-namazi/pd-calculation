package ir.pd.calc.base

import groovy.util.logging.Slf4j
import ir.pd.Calculation
import ir.pd.Request
import ir.pd.calc.CalcItem
import ir.pd.calc.CalculationType

import static ir.pd.VehicleBodyType.BUS
import static ir.pd.calc.CalculationType.BASE

@Slf4j
class DefaultBaseBus1400 implements CalcItem {

    @Override
    CalculationType calculationTyp() {
        BASE
    }

    @Override
    boolean match(Request request) {
        request.bodyType == BUS
    }

    @Override
    Calculation apply(Calculation calculation) {
        calculation.next(calculationTyp())
                .with({ r, c -> 200.0 })
                .detail({ Request r, Calculation c -> [type: 'BASE', bodyType: 'BUS', base: c.get(BASE)] })
                .end()
    }
}
