package com.coop.votation.service;

import com.coop.votation.repository.PautaRepository;
import com.coop.votation.repository.SessaoVotacaoRepository;
import com.coop.votation.repository.VotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class VotacaoServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotacaoService votacaoService;

    @Test
    @DisplayName("Deve inicializar o VotacaoService com sucesso via Mockito")
    void deveInicializarService() {
        assertNotNull(votacaoService, "O serviço de votação não deveria ser nulo");
    }
}