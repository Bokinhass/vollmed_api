package med.voll.api.domain.makeQuery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MakeQueryRepository extends JpaRepository<MakeQuery, Long> {

  Boolean existsByMedicIdAndData(Long idMedico, LocalDateTime date);
}
