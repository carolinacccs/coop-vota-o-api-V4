package com.coop.votation.web;

import com.coop.votation.domain.Pauta;
import com.coop.votation.domain.SessaoVotacao;
import com.coop.votation.dto.*;
import com.coop.votation.service.VotacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final VotacaoService votacaoService;

    @PostMapping
    public ResponseEntity<com.coop.votation.dto.PautaResponse> cadastrarPauta(@RequestBody @Valid PautaRequest request) {
        com.coop.votation.dto.PautaResponse novaPauta = votacaoService.criarPauta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPauta);
    }

    @GetMapping
    public ResponseEntity<TelaSelecaoSDUI> listarPautasSDUI() {
        return ResponseEntity.ok(votacaoService.getTelaSelecaoPautas());
    }

    @PostMapping("/{id}/abrir")
    public ResponseEntity<com.coop.votation.dto.SessaoResponse> abrirSessao(
            @PathVariable UUID id,
            @RequestBody(required = false) AbrirSessaoRequest request) {
        Long minutos = (request != null) ? request.getTempoEmMinutos() : null;
        com.coop.votation.dto.SessaoResponse resp = votacaoService.abrirSessao(id, minutos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}/votar")
    public ResponseEntity<TelaFormularioSDUI> getTelaVotacaoSDUI(@PathVariable UUID id) {
        return ResponseEntity.ok(votacaoService.getTelaVotacaoForm(id));
    }

    @PostMapping("/{id}/votos")
    public ResponseEntity<Void> registrarVoto(
            @PathVariable UUID id,
            @RequestBody @Valid VotoRequest request) {
        votacaoService.votar(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<ResultadoVotacaoResponse> obterResultado(@PathVariable UUID id) {
        return ResponseEntity.ok(votacaoService.obterResultado(id));
    }
}