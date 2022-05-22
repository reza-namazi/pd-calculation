package ir.pd.calc

import groovy.util.logging.Slf4j
import ir.pd.Calculation

@Slf4j
trait AbstractCalculator {

    abstract CalcItem[] items()

    Calculation doCalculate(Calculation c) {
        calculate(c)
    }

    def calculate = { Calculation c ->
        items()
                .findAll { it.match c.request }
                .eachWithIndex { it, idx -> log.debug('{} : {}', idx, it.calculationTyp() as String) }
                .inject(c) { cc, item -> item.apply cc }
                .eval()
    }
}