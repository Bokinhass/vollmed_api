package med.voll.api.domain.makeQuery.validations;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.makeQuery.DataDetailMakeQuery;

import java.time.DayOfWeek;

public class ValidateFunctionClinic {

  public void validate(DataDetailMakeQuery data) {

    var dateAppoint = data.date();

    var domingo = dateAppoint.getDayOfWeek().equals(DayOfWeek.SUNDAY);

    var beforeOpenClinic = dateAppoint.getHour() < 7;
    var afterCloseClinic = dateAppoint.getHour() > 18;

    if (domingo || beforeOpenClinic || afterCloseClinic) {
      throw new ValidationException("Appoint outside opening hours.");
    }
  }
}
