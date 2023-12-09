package med.voll.api.domain.makeQuery.validations;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.makeQuery.DataDetailMakeQuery;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidateBefore {

  public void validate(DataDetailMakeQuery data) {

    var dateAppoint = data.date();

    var now = LocalDateTime.now();

    var diff = Duration.between(now, dateAppoint).toMinutes();

    if (diff < 30) {
      throw new ValidationException("Appoint near to hour, need 30 minutes before.");
    }
  }
}
