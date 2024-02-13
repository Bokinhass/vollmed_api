package med.voll.api.domain.medic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
  Page<Medico> findAllByActiveTrue(Pageable paginacao);

  @Query("""
          select m from Medico m
          where
          m.active = true
          and
          m.especialidade = :especialidade
          and
          m.id not in(
            select c.medico.id from Consulta c
            where
            c.data = :date
          )
          order by rand()
          limit 1
          """)
  Medico chooseMedicRandomAtDate(Especialidade especialidade, LocalDateTime date);

  @Query("""
          select m.active
          from Medico m
          where
          m.id =:id
          """)
  Boolean findActiveTrue(Long idMedico);
}
