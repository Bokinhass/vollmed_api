package med.voll.api.domain.makeQuery;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medic.Especialidade;

import java.time.LocalDateTime;

public record DataMakeQuery(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime date,

        Especialidade especialidade
) {
}
