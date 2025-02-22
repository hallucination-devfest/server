package com.cheep.hallucination.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "room_tb")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Column(name = "round")
    private Integer round;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "question_count")
    private Integer questionCount;

    //-------------------------------

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "keyword_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Keyword keyword;

    @JoinColumn(name = "liar_character_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Character liarCharacter;

    //-----------------------------------------------

    @Builder
    public Room(Integer round, User user, Keyword keyword, Character liarCharacter, Integer questionCount) {
        this.isSuccess = false;
        this.round = round;
        this.createdAt = LocalDateTime.now();
        this.user = user;
        this.keyword = keyword;
        this.liarCharacter = liarCharacter;
        this.questionCount = questionCount;
    }

    public boolean updateSuccess() {
        this.isSuccess = true;
        return true;
    }
}
