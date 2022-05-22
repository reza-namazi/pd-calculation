package ir.pd.enricher

import ir.pd.Request

import java.time.Instant
import java.util.function.Function

class DefaultRequestEnricher implements Function<Request, Request> {

    /**
     * Add As many as needed attributes to the request which needs database access, like reading:
     * the tax percent
     * the no claim rates and etc...
     * */
    @Override
    Request apply(Request request) {
        def today = request.currentDate
        def taxRate = fetchTaxRateOf today
        def numberOfNoClaimRate = numberOfNoClaimRate request, today

        request.copyWith(
                numberOfNoClaimRate: numberOfNoClaimRate,
                tax: taxRate)
    }

    /**
     * for example fetch tax rate from database based on passed date.
     * */
    static def fetchTaxRateOf(Instant date) {
        return 10 / 100 * 10
    }

    static def numberOfNoClaimRate(Request request, Instant date) {
        return request.numberOfNoClaimYears / 100 * 10
    }
}
