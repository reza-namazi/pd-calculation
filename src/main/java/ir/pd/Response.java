package ir.pd;

import java.util.List;

public abstract class Response {

  public abstract boolean isSuccess();

  public static InvalidResponse of(List<String> errors) {
    return new InvalidResponse(errors);
  }

  public static ValidResponse of(Calculation calculation) {
    return new ValidResponse(calculation);
  }
}

class ValidResponse extends Response {
  public final Calculation result;

  public ValidResponse(Calculation result) {
    this.result = result;
  }

  @Override
  public boolean isSuccess() {
    return true;
  }
}

class InvalidResponse extends Response {
  public final List<String> errors;

  public InvalidResponse(List<String> errors) {
    this.errors = errors;
  }

  @Override
  public boolean isSuccess() {
    return false;
  }
}