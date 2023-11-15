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

  public void updateEndInfos(DadosEndereco data) {
    if (data.logradouro() != null) {
      this.logradouro = data.logradouro();
    }

    if (data.bairro() != null) {
      this.bairro = data.bairro();
    }

    if (data.cep() != null) {
      this.cep = data.cep();
    }

    if (data.cidade() != null) {
      this.cidade = data.cidade();
    }

    if (data.uf() != null) {
      this.uf = data.uf();
    }

    if (data.complemento() != null) {
      this.complemento = data.complemento();
    }

    if (data.numero() != null) {
      this.numero = data.numero();
    }
  }
}
