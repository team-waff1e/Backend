package io.github.teamwaff1e.waffle.domain.likes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes implements Serializable {
    @Id
    @NotNull
    private Long memberId;

    @Id
    @NotNull
    private Long waffleId;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    protected Likes(Long memberId, Long waffleId) {
        this.memberId = memberId;
        this.waffleId = waffleId;
    }
}