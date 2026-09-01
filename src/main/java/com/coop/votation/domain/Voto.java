package com.coop.votation.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "tb_voto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pauta_id", "cpfAssociado"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private String cpfAssociado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OpcaoVoto voto;
}