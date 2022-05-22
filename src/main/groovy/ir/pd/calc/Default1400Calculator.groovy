package ir.pd.calc

import groovy.util.logging.Slf4j
import ir.pd.calc.base.DefaultBaseBus1400
import ir.pd.calc.base.DefaultBaseSedan1400
import ir.pd.calc.claim.NoClaim1400
import ir.pd.calc.tax.DefaultTax1400
import ir.pd.calc.total.DefaultTotal1400

@Slf4j
class Default1400Calculator implements AbstractCalculator {

    @Override
    CalcItem[] items() {
        [new DefaultBaseBus1400(),
         new DefaultBaseSedan1400(),
         new NoClaim1400(),
         new DefaultTax1400(),
         new DefaultTotal1400()]
    }
}
