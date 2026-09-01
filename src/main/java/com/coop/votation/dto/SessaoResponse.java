package com.coop.votation.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoResponse {
    private UUID id;
    private UUID pautaId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
