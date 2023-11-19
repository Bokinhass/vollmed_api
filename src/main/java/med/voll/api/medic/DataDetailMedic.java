package med.voll.api.medic;

import med.voll.api.address.Endereco;

public record DataDetailMedic (
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
  ) {

    public DataDetailMedic (Medico medico) {
      this(
              medico.getId(),
              medico.getNome(),
              medico.getEmail(),
              medico.getCrm(),
              medico.getTelefone(),
              medico.getEspecialidade(),
              medico.getEndereco()
      );
    }
}