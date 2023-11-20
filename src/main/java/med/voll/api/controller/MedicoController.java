package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medic.DadosListaMedico;
import med.voll.api.domain.medic.DataDetailMedic;
import med.voll.api.domain.medic.Medico;
import med.voll.api.domain.medic.MedicoRepository;
import med.voll.api.domain.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medic")
public class MedicoController {

  @Autowired
  private MedicoRepository repository;

  @PostMapping
  @Transactional
  public ResponseEntity register(@RequestBody @Valid DataMedico data, UriComponentsBuilder uriBuilder) {

    var medico = new Medico(data);
    repository.save(medico);

    var uri = uriBuilder.path("/medic/{id}").buildAndExpand(medico.getId()).toUri();

    var dto = new DataDetailMedic(medico);

    return ResponseEntity.created(uri).body(dto);
  }

  @GetMapping
  public ResponseEntity<Page<DadosListaMedico>> getMedicos(
          @PageableDefault(size = 10, sort = {"id"})
          Pageable paginacao) {
    var page = repository.findAllByActiveTrue(paginacao)
            .map(DadosListaMedico::new);

    return ResponseEntity.ok(page);
  }

  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity updateMedicos(@PathVariable Long id, @RequestBody @Valid DataUpateMedico data) {
    var medico = repository.getReferenceById(data.id());
    medico.updateInfos(data);

    return ResponseEntity.ok(new DataDetailMedic(medico));
  }

  @DeleteMapping("/delete/{id}")
  @Transactional
  public ResponseEntity deleteMedico(@PathVariable Long id) {
    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/disable/{id}")
  @Transactional
  public ResponseEntity disableMedico(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.disable();

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/albe/{id}")
  @Transactional
  public ResponseEntity ableMedico(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.able();

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity getMedicoById(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);

    return ResponseEntity.ok(new DataDetailMedic(medico));
  }
}
