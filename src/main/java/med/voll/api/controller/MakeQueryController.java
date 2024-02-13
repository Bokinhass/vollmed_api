package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.makeQuery.DataDetailMakeQuery;
import med.voll.api.domain.makeQuery.DataMakeQuery;
import med.voll.api.domain.makeQuery.MakeAppoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "consultas")
public class MakeQueryController {

  @Autowired
  private MakeAppoint appoint;

  @PostMapping
  @Transactional
  public ResponseEntity makeQuery(@RequestBody @Valid DataMakeQuery data) {
    appoint.appointment(data);

    return ResponseEntity.ok(new DataDetailMakeQuery(null, null, null, null));
  }
}
