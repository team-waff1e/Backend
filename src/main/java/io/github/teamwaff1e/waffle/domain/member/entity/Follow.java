package io.github.teamwaff1e.waffle.domain.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {


    @Id
    @NotNull
    private Long memberId;
    @Id
    @NotNull
    private Long followingId;

    private Timestamp createdAt;


    @Builder
    protected Follow(Long memberId,Long followingId) {
        this.followingId = followingId;
        this.memberId = memberId;
    }


    @PrePersist
    public void prePersist() {createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @Override
    public String toString(){
        return memberId + " to " + followingId;
    }

}
