package com.coop.votation.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponse {
    private UUID id;
    private String titulo;
    private String descricao;
}
