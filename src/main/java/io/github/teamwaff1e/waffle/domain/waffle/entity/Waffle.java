package io.github.teamwaff1e.waffle.domain.waffle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서의 생성을 열어 둘 필요 ..? x
@Getter
public class Waffle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long likesCount;
    private Long commentCount;

    //    @ManyToOne  // TODO auth 관련 처리 시작시 Member 객체로 교체
    //    @JoinColumn(name = "member_id")
    private Long memberId;


    @Builder
    protected Waffle(Long id, String content, Long likesCount, Long commentCount, Long memberId) {
        this.id = id;
        this.content = content;
        this.likesCount = likesCount;
        this.commentCount = commentCount;
        this.memberId = memberId;

    }

    @PrePersist
    public void prePersist() {
        updatedAt = createdAt = Timestamp.valueOf(LocalDateTime.now());
        likesCount = commentCount = 0L;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.toLocalDateTime();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt.toLocalDateTime();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void updateWaffleContent(String content) {
        this.content = content;
    }

    public void like() {  // TODO auth -> 동일인 like, unlilke 1회성 여부 처리 로직 추가하기
        if(this.likesCount+1 < Long.MAX_VALUE) this.likesCount++;
    }

    public void unlike() {
        if(this.likesCount > 0L) this.likesCount--;
    }

    public void addComment() { if(this.commentCount+1 < Long.MAX_VALUE) this.commentCount++; }
    public void deleteComment() { if(this.commentCount > 0L) this.commentCount--; }
}
