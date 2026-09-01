package com.coop.votation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemSelecaoSDUI {
    private String id;
    private String titulo;
    private String subTitulo;
    private String urlAction;
    private Map<String, Object> body;
}