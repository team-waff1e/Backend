package io.github.teamwaff1e.waffle.domain.comment.entity;

import jakarta.persistence.*;
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
    private String profile_url;

    private Timestamp createdAt;
    private Timestamp updatedAt;


    @Builder
    protected Member(String content, String pwd, String name, String nickname) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
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
