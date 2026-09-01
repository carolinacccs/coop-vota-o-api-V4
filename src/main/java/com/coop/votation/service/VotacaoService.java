package com.coop.votation.service;

import com.coop.votation.domain.*;
import com.coop.votation.dto.*;
import com.coop.votation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final VotoRepository votoRepository;
    private final CpfValidationService cpfValidationService;

    @Value("${sdui.callback-base:}")
    private String sduiCallbackBase;

    private String cb(String path) {
        if (sduiCallbackBase == null || sduiCallbackBase.isBlank()) return path;
        if (sduiCallbackBase.endsWith("/") && path.startsWith("/")) {
            return sduiCallbackBase.substring(0, sduiCallbackBase.length() - 1) + path;
        } else if (!sduiCallbackBase.endsWith("/") && !path.startsWith("/")) {
            return sduiCallbackBase + "/" + path;
        } else {
            return sduiCallbackBase + path;
        }
    }

    public PautaResponse criarPauta(PautaRequest request) {
        Pauta pauta = Pauta.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .build();
        Pauta saved = pautaRepository.save(pauta);
        return PautaResponse.builder()
                .id(saved.getId())
                .titulo(saved.getTitulo())
                .descricao(saved.getDescricao())
                .build();
    }

    public SessaoResponse abrirSessao(UUID pautaId, Long minutos) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada."));

        sessaoVotacaoRepository.findByPautaId(pautaId).ifPresent(s -> {
            throw new IllegalStateException("Já existe uma sessão cadastrada para esta pauta.");
        });

        long duracao = (minutos != null && minutos > 0) ? minutos : 1L;

        SessaoVotacao sessao = SessaoVotacao.builder()
                .pauta(pauta)
                .dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().plusMinutes(duracao))
                .build();

        SessaoVotacao saved = sessaoVotacaoRepository.save(sessao);

        return SessaoResponse.builder()
                .id(saved.getId())
                .pautaId(saved.getPauta().getId())
                .dataInicio(saved.getDataInicio())
                .dataFim(saved.getDataFim())
                .build();
    }

    @Transactional
    public void votar(UUID pautaId, VotoRequest request) {
        SessaoVotacao sessao = sessaoVotacaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada para a pauta."));

        if (!sessao.isAberta()) {
            throw new IllegalStateException("A sessão de votação para esta pauta está encerrada.");
        }

        if (votoRepository.existsByPautaIdAndCpfAssociado(pautaId, request.getCpf())) {
            throw new IllegalStateException("Associado já votou nesta pauta.");
        }

        if (!cpfValidationService.canVote(request.getCpf())) {
            throw new IllegalStateException("Associado não está apto para votar (UNABLE_TO_VOTE).");
        }

        Voto voto = Voto.builder()
                .pauta(sessao.getPauta())
                .cpfAssociado(request.getCpf())
                .voto(request.getVoto())
                .build();

        votoRepository.save(voto);
    }

    public ResultadoVotacaoResponse obterResultado(UUID pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada."));

        Optional<SessaoVotacao> sessaoOpt = sessaoVotacaoRepository.findByPautaId(pautaId);

        String status = sessaoOpt.map(s -> s.isAberta() ? "EM_ANDAMENTO" : "FINALIZADA")
                .orElse("NAO_INICIADA");

        long totalSim = votoRepository.countByPautaIdAndVoto(pautaId, OpcaoVoto.SIM);
        long totalNao = votoRepository.countByPautaIdAndVoto(pautaId, OpcaoVoto.NAO);

        return ResultadoVotacaoResponse.builder()
                .pautaId(pauta.getId())
                .tituloPauta(pauta.getTitulo())
                .totalSim(totalSim)
                .totalNao(totalNao)
                .statusSessao(status)
                .build();
    }

    public TelaSelecaoSDUI getTelaSelecaoPautas() {
        List<Pauta> pautas = pautaRepository.findAll();
        List<ItemSelecaoSDUI> itens = pautas.stream().map(p -> {
            Map<String, Object> body = Map.of("pautaId", p.getId().toString());
            return new ItemSelecaoSDUI(
                    p.getId().toString(),
                    p.getTitulo(),
                    p.getDescricao(),
                    cb("/v1/pautas/" + p.getId() + "/votar"),
                    body
            );
        }).toList();

        return TelaSelecaoSDUI.builder()
                .tipo("SELECAO")
                .titulo("Selecione uma Pauta para Votar")
                .itens(itens)
                .build();
    }

    public TelaFormularioSDUI getTelaVotacaoForm(UUID pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada."));

        List<CampoSDUI> campos = List.of(
                new CampoSDUI("cpf", "CPF do Associado", "TEXTO")
        );

        List<BotaoSDUI> botoes = List.of(
                new BotaoSDUI("Votar SIM", cb("/v1/pautas/" + pautaId + "/votos"), Map.of("voto", "SIM")),
                new BotaoSDUI("Votar NÃO", cb("/v1/pautas/" + pautaId + "/votos"), Map.of("voto", "NAO"))
        );

        return TelaFormularioSDUI.builder()
                .tipo("FORMULARIO")
                .titulo("Votação: " + pauta.getTitulo())
                .campos(campos)
                .botoes(botoes)
                .build();
    }
}