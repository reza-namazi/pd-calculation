package ir.pd.calc

import groovy.util.logging.Slf4j
import ir.pd.Calculation
import ir.pd.Request

@Slf4j
trait AbstractCalculator {

    abstract CalcItem[] items()

    def calculate = { Request r, Calculation c ->
        items()
                .findAll { it.match(r) }
                .eachWithIndex { it, idx -> log.debug('{} : {}', idx, it.calculationTyp() as String) }
                .inject(c) { cc, item ->  item.apply(cc) }
                .eval()
    }
}