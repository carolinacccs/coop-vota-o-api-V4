package com.coop.votation.repository;

import com.coop.votation.domain.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, UUID> {
    Optional<SessaoVotacao> findByPautaId(UUID pautaId);
}