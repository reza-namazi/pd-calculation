package ir.pd.validator;

import io.vavr.Value;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import ir.pd.Request;
import ir.pd.UsageType;
import ir.pd.VehicleBodyType;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;
import static ir.pd.VehicleBodyType.MINI_TRUCK;
import static ir.pd.VehicleBodyType.PICKUP;
import static ir.pd.VehicleBodyType.SEDAN;
import static ir.pd.VehicleBodyType.TRUCK;
import static ir.pd.VehicleBodyType.VAN;
import static java.util.Arrays.asList;

public class DefaultRequestValidator {

  public Validation<List<String>, Request> validate(Request request) {
    return Validation
      .combine(
        validateNumberOfNoClaimYears(request),
        validateBodyTypeMatchesUsageType(request)
      ).ap((a, b) -> request)
      .mapError(Seq::toJavaList);
  }

  private Validation<String, Integer> validateNumberOfNoClaimYears(Request request) {
    return request.getNumberOfNoClaimYears() < 0 || request.getNumberOfNoClaimYears() > 100 ?
      invalid("Invalid noClaimYears. It must be between (0..100)") :
      valid(request.getNumberOfNoClaimYears());
  }

  private Validation<String, Boolean> validateBodyTypeMatchesUsageType(Request request) {
    Set<VehicleBodyType> result = mapUsageType(request.getUsageType());
    return result.contains(request.getBodyType()) ? valid(true) : invalid("Vehicle UsageType does not match the vehicle body type");
  }

  private Set<VehicleBodyType> mapUsageType(UsageType usageType) {
    switch (usageType) {
      case PERSONAL:
        return setOf.apply(asList(SEDAN, PICKUP));
      case TAXI:
        return setOf.apply(asList(SEDAN, VAN));
      case CARGO_CARRY:
        return setOf.apply(asList(VAN, MINI_TRUCK, TRUCK));
      default:
        return Collections.emptySet();
    }
  }

  Function<List<VehicleBodyType>, Set<VehicleBodyType>> setOf = HashSet::new;
}
