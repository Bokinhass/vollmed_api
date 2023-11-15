package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/medic")
public class MedicoController {

  @Autowired
  private MedicoRepository repository;

  @PostMapping
  @Transactional
  public void register(@RequestBody @Valid DataMedico data) {
    repository.save(new Medico(data));
  }

  @GetMapping
  public Page<DadosListaMedico> getMedicos(
          @PageableDefault(size = 10, sort = {"id"})
          Pageable paginacao) {
    return repository.findAllByActiveTrue(paginacao)
            .map(DadosListaMedico::new);
  }

  @PutMapping("/update/{id}")
  @Transactional
  public void updateMedicos(@PathVariable Long id, @RequestBody @Valid DataUpateMedico data) {
    var medico = repository.getReferenceById(data.id());
    medico.updateInfos(data);
  }

  @DeleteMapping("/delete/{id}")
  @Transactional
  public void deleteMedico(@PathVariable Long id) {
    repository.deleteById(id);
  }

  @PutMapping("/disable/{id}")
  @Transactional
  public void disableMedico(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.disable();
  }

  @PutMapping("/albe/{id}")
  @Transactional
  public void ableMedico(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.able();
  }
}
