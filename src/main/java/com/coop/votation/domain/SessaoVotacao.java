package com.coop.votation.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_sessao_votacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "pauta_id", nullable = false, unique = true)
    private Pauta pauta;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataFim;

    public boolean isAberta() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(dataInicio) && agora.isBefore(dataFim);
    }
}