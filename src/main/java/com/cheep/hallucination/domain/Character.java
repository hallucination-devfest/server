package com.cheep.hallucination.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "character_tb")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //------------------------------------

    @OneToMany(mappedBy = "character", cascade = CascadeType.MERGE)
    private List<Personalities> personalities;

    @OneToMany(mappedBy = "character", cascade = CascadeType.MERGE)
    private List<RoomChat> roomChats;

    @OneToMany(mappedBy = "character", cascade = CascadeType.MERGE)
    private List<RoomInterview> roomInterviews;

}
