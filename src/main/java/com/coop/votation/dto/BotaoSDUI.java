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
public class BotaoSDUI {
    private String texto;
    private String urlAction;
    private Map<String, Object> body;
}