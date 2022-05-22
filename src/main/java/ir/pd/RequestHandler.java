package ir.pd;

import io.vavr.control.Either;
import ir.pd.calc.Default1400Calculator;
import ir.pd.enricher.DefaultRequestEnricher;
import ir.pd.validator.DefaultRequestValidator;

public class RequestHandler {

  DefaultRequestEnricher requestEnricher = new DefaultRequestEnricher();
  DefaultRequestValidator validator = new DefaultRequestValidator();
  Default1400Calculator default1400Calculator = new Default1400Calculator();

  public Either<InvalidResponse, ValidResponse> handle(Request request) {

    return validator.validate(request)
      .mapError(Response::of)
      .toEither()
      .map(requestEnricher)
      .map(Calculation::new)
      .map(default1400Calculator::doCalculate)
      .map(Response::of);

  }
}
