package ir.pd.calc

import ir.pd.Calculation
import ir.pd.Request

import java.util.function.Function

trait CalcItem implements Function<Calculation, Calculation> {

    abstract CalculationType calculationTyp()

    abstract boolean match(Request request)

}