package com.cheep.hallucination.domain;

import com.cheep.hallucination.domain.type.EProvider;
import com.cheep.hallucination.domain.type.ERole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_tb", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"serial_id", "provider"})
})
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "serial_id", nullable = false)
    private String serialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private EProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole role;

    @Column(name = "refresh_Token")
    private String refreshToken;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name =  "nickname", nullable = false)
    private String nickname;


    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "chance_play", nullable = false)
    private Integer chancePlay;

    //------------------------------------

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Room> rooms;

    //------------------------------------

    @Builder
    public User(
            String serialId,
            EProvider provider,
            ERole role,
            String nickname,
            String password,
            String email
    ) {
        this.serialId = serialId;
        this.provider = provider;
        this.role = role;
        this.nickname = nickname;
        this.password = password;
        this.refreshToken = null;
        this.email = email;
        this.chancePlay = 1;
    }

    //------------------------------------

}
