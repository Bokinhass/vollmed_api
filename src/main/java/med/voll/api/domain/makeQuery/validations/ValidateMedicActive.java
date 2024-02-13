package med.voll.api.domain.makeQuery.validations;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.makeQuery.DataDetailMakeQuery;
import med.voll.api.domain.medic.MedicoRepository;

public class ValidateMedicActive {

  private MedicoRepository repository;

  public void validate(DataDetailMakeQuery data) {

    if (data.idMedico() == null) {
      return;
    }

    var medicActive = repository.findActiveTrue(data.idMedico());
    if (!medicActive) {
      throw new ValidationException("Appoint not be able with medic not active.");
    }
  }
}
