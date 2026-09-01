package com.coop.votation.repository;

import com.coop.votation.domain.OpcaoVoto;
import com.coop.votation.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VotoRepository extends JpaRepository<Voto, UUID> {
    boolean existsByPautaIdAndCpfAssociado(UUID pautaId, String cpfAssociado);
    long countByPautaIdAndVoto(UUID pautaId, OpcaoVoto voto);
}