package med.voll.api.domain.makeQuery;


import jakarta.validation.Valid;
import med.voll.api.domain.ValidationException;
import med.voll.api.domain.medic.Medico;
import med.voll.api.domain.medic.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeAppoint {

  @Autowired
  private MakeQueryRepository makeQueryRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;


  public void appointment(@Valid DataMakeQuery data) {

    if (!pacienteRepository.existsById(data.idPaciente())) {
      throw new ValidationException("Id patient not found!");
    }

    if (data.idMedico() != null && !medicoRepository.existsById(data.idMedico())) {
      throw new ValidationException("Id medic not found!");
    }

    var paciente = pacienteRepository.findById(data.idPaciente()).get();
    var medico = chooseMedic(data);
    var appointment = new MakeQuery(null, medico, paciente, data.date());


    makeQueryRepository.save(appointment);
  }

  private Medico chooseMedic(DataMakeQuery data) {

    if (data.idMedico() != null) {
      return medicoRepository.getReferenceById(data.idMedico());
    }

    if (data.especialidade() == null) {
      throw new ValidationException("Especialidade is required.");
    }

    return (Medico) medicoRepository.chooseMedicRandomAtDate(data.especialidade(), data.date());
  }
}
