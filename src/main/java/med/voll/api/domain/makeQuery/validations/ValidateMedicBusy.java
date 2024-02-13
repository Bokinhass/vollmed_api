package med.voll.api.domain.makeQuery.validations;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.makeQuery.DataDetailMakeQuery;
import med.voll.api.domain.makeQuery.MakeQueryRepository;

public class ValidateMedicBusy {

  private MakeQueryRepository repository;

  public void validate(DataDetailMakeQuery data) {

    var medicBusySameTime = repository.existsByMedicIdAndData(data.idMedico(), data.date());

    if (medicBusySameTime) {
      throw new ValidationException("Medic is busy at this time.");
    }
  }
}