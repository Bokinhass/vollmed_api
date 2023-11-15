package med.voll.api.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.DadosEndereco;

public record DataUpateMedico(

        @NotNull
        Long id,

        String nome,
        String telefone,
        DadosEndereco endereco
        ) {
}
