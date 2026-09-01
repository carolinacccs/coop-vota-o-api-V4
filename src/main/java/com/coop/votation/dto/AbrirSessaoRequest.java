package com.coop.votation.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbrirSessaoRequest {
    private Long tempoEmMinutos;
}