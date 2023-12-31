package io.github.teamwaff1e.waffle.domain.waffle.entity;

import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@DynamicInsert
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
    protected Waffle(String content, Member member) {
        this.content = content;
        this.member = member;
    }

    public void updateWaffleContent(String content) {
        this.content = content;
    }

    public void like() {
        if (likesCount + 1L == Long.MAX_VALUE) {
            throw new IllegalStateException();
        }
        likesCount++;
    }

    public void unlike() {
        if (likesCount <= 0L) {
            throw new IllegalStateException();
        }
        likesCount--;
    }

    public void increaseCommentCount() {
        if (commentCount + 1L == Long.MAX_VALUE) {
            throw new IllegalStateException();
        }

        commentCount++;
    }

    public void decreaseCommentCount() {
        if (commentCount <= 0L) {
            throw new IllegalStateException();
        }

        commentCount--;
    }
}
