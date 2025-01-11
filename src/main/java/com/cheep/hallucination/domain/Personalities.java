package com.cheep.hallucination.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "personalities_tb")
public class Personalities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cautiousness", nullable = false)
    private Integer cautiousness;

    @Column(name = "creativity", nullable = false)
    private Integer creativity;

    @Column(name = "logic", nullable = false)
    private Integer logic;

    @Column(name = "humor", nullable = false)
    private Integer humor;

    //-----------------------------------------

    @JoinColumn(name = "character_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Character character;
}
