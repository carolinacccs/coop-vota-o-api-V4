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
public class TelaSelecaoSDUI {
    private String tipo; // "SELECAO"
    private String titulo;
    private List<ItemSelecaoSDUI> itens;
}