package med.voll.api.medic;

import med.voll.api.address.DataAddress;

public record DataMedics(
        String nome,
        String email,
        String crm,
        Specialty especialidade,
        DataAddress endereco
) {
}
