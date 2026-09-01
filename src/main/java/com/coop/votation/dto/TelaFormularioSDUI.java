package com.coop.votation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelaFormularioSDUI {
    private String tipo; // "FORMULARIO"
    private String titulo;
    private List<CampoSDUI> campos;
    private List<BotaoSDUI> botoes;
}