package com.coop.votation.dto;

import com.coop.votation.domain.OpcaoVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoRequest {
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @NotNull(message = "O voto (SIM/NAO) é obrigatório.")
    private OpcaoVoto voto;
}