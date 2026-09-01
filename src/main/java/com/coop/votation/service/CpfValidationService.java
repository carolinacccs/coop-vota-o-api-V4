package com.coop.votation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Service
public class CpfValidationService {

    private final WebClient webClient;

    public CpfValidationService(@Value("${external.user-info.url}") String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public boolean canVote(String cpf) {
        try {
            Map<?, ?> response = this.webClient.get()
                    .uri("/users/{cpf}", cpf)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return response != null && "ABLE_TO_VOTE".equals(response.get("status"));
        } catch (WebClientResponseException.NotFound e) {
            throw new IllegalArgumentException("CPF inválido ou não encontrado.");
        } catch (Exception e) {
            // Em caso de instabilidade na API externa, libera o voto para resiliência do sistema
            return true;
        }
    }
}