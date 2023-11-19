package med.voll.api.domain.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.DadosEndereco;

public record DataUpateMedico(

        @NotNull
        Long id,

        String nome,
        String telefone,
        DadosEndereco endereco
        ) {
}
