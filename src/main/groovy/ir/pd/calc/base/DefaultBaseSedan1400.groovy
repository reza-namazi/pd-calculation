package ir.pd.calc.base

import groovy.util.logging.Slf4j
import ir.pd.Calculation
import ir.pd.Request
import ir.pd.calc.CalcItem
import ir.pd.calc.CalculationType

import static ir.pd.VehicleBodyType.SEDAN
import static ir.pd.calc.CalculationType.BASE

@Slf4j
class DefaultBaseSedan1400 implements CalcItem {


    CalculationType calculationTyp() {
        BASE
    }

    @Override
    boolean match(Request request) {
        request.bodyType == SEDAN
    }

    @Override
    Calculation apply(Calculation calculation) {
        calculation.next(calculationTyp())
                .with({ r, c -> 100.0 })
                .detail({ Request r, Calculation c -> [type: 'BASE', bodyType: 'SEDAN', base: c.get(BASE)] })
                .end()
    }

}
