package io.github.teamwaff1e.waffle.domain.waffle.entity;

import io.github.teamwaff1e.waffle.domain.member.entity.Member;
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
public class Waffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long likesCount;
    private Long commentCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    protected Waffle(Long id, String content, Long likesCount, Member member) {
        this.id = id;
        this.content = content;
        this.likesCount = likesCount;
        this.member = member;

    }

    public void updateWaffleContent(String content) {
        this.content = content;
    }

    public void like() {
        if (this.likesCount + 1L < Long.MAX_VALUE) {
            this.likesCount++;
        }
    }

    public void unlike() {
        if (this.likesCount > 0L) {
            this.likesCount--;
        }
    }
}
