package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
  private String logradouro;
  private String bairro;
  private String cep;
  private String cidade;
  private String uf;
  private String complemento;
  private String numero;

  public Endereco(DadosEndereco data) {
    this.logradouro = data.logradouro();
    this.bairro = data.bairro();
    this.cep = data.cep();
    this.cidade = data.cidade();
    this.uf = data.uf();
    this.complemento = data.complemento();
    this.numero = data.numero();
  }
}
