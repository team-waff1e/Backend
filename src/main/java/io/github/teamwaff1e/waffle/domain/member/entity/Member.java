package io.github.teamwaff1e.waffle.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String pwd;
    private String name;
    private String nickname;
    private String profileUrl;

    private Long followingCount;
    private Long followerCount;
    private Long waffleCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


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
}
