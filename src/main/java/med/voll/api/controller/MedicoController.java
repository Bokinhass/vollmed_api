package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medic.DadosListaMedico;
import med.voll.api.medic.DataMedico;
import med.voll.api.medic.Medico;
import med.voll.api.medic.MedicoRepository;
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
          @PageableDefault(size = 10, sort = {"nome"})
          Pageable paginacao) {
    return repository.findAll(paginacao)
            .map(DadosListaMedico::new);
  }
}
