package med.voll.api.domain.makeQuery;

import java.time.LocalDateTime;

public record DataDetailMakeQuery(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime date
) {
}
