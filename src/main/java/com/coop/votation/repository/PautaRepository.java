package com.coop.votation.repository;

import com.coop.votation.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {
}