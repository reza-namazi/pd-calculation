package ir.pd.calc.tax

import ir.pd.Calculation
import ir.pd.Request
import ir.pd.calc.CalcItem
import ir.pd.calc.CalculationType

import static ir.pd.calc.CalculationType.*

class DefaultTax1400 implements CalcItem {

    @Override
    CalculationType calculationTyp() {
        TAX
    }

    @Override
    boolean match(Request request) {
        true
    }


/*
    def calc = { Request r, Calculation c ->
        def value = r.tax / 100 * 10 * (c.base + c.noClaimDiscount) + c.base + c.noClaimDiscount
        c.copyWith(tax: value, acc: c.acc + value, operation: 'tax')
    }
*/

    @Override
    Calculation apply(Calculation calculation) {
        calculation.next(TAX)
                .with({ Request r, Calculation c -> r.tax / 100 * 10 * (c.get(BASE) + c.get(NO_CLAIM)) + c.get(BASE) + c.get(NO_CLAIM) })
                .detail({ Request r, Calculation c -> [type: 'TAX', base: c.get(BASE), noClaim: c.get(NO_CLAIM), tax: c.get(TAX)] })
                .end()
    }
/*
    @Override
    Calculation apply(Request r, Calculation c) {
        def value = r.tax / 100 * 10 * (c.base + c.noClaimDiscount) + c.base + c.noClaimDiscount
        c.copyWith(tax: value, acc: c.acc + value, operation: 'tax')
    }*/
}
