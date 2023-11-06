package io.github.teamwaff1e.waffle.domain.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String email;

    private String pwd;
    private String name;
    private String nickname;

    private String profileUrl;

    private Timestamp createdAt;
    private Timestamp updatedAt;


    @Builder
    protected Member(String email, String pwd, String name, String nickname) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    @PrePersist
    public void prePersist() {
        updatedAt = createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
