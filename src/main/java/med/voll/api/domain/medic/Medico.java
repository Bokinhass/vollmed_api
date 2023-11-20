package med.voll.api.domain.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Endereco;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private String crm;

  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;

  @Embedded
  private Endereco endereco;

  private Boolean active;

  public Medico(DataMedico data) {
    this.active = true;
    this.nome = data.nome();
    this.email = data.email();
    this.telefone = data.telefone();
    this.crm = data.crm();
    this.especialidade = data.especialidade();
    this.endereco = new Endereco(data.endereco());
  }

  public void updateInfos(DataUpateMedico data) {
    if (data.nome() != null) {
      this.nome = data.nome();
    }

    if (data.telefone() != null) {
      this.telefone = data.telefone();
    }

    if (data.endereco() != null) {
      this.endereco.updateEndInfos(data.endereco());
    }
  }

  public void disable() {
    this.active = false;
  }

  public void able() {
    this.active = true;
  }
}