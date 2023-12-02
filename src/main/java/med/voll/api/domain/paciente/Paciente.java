package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Endereco;


@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;

  private String telefone;

  private String cpf;

  @Embedded
  private Endereco endereco;

  private Boolean ativo;

  public Paciente(DadosCadastroPaciente data) {
    this.ativo = true;
    this.nome = data.nome();
    this.email = data.email();
    this.telefone = data.telefone();
    this.cpf = data.cpf();
    this.endereco = new Endereco(data.endereco());
  }

  public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
    if (dados.nome() != null) {
      this.nome = dados.nome();
    }
    if (dados.telefone() != null) {
      this.telefone = dados.telefone();
    }
    if (dados.endereco() != null) {
      this.endereco.updateEndInfos(dados.endereco());
    }

  }

  public void excluir() {
    this.ativo = false;
  }
}
