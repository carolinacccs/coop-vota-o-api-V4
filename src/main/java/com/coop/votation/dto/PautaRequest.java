package com.coop.votation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PautaRequest {
    @NotBlank(message = "O título é obrigatório.")
    private String titulo;
    private String descricao;
}