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
    private Long likes_count;
    private Long comment_count;

    //    @ManyToOne  // TODO auth 관련 처리 시작시 Member 객체로 교체
    //    @JoinColumn(name = "member_id")
    private Long memberId;


    @Builder
    protected Waffle(Long id, String content, Long likes_count, Long comment_count, Long memberId) {
        this.id = id;
        this.content = content;
        this.likes_count = likes_count;
        this.comment_count = comment_count;
        this.memberId = memberId;

    }

    @PrePersist
    public void prePersist() {
        updatedAt = createdAt = Timestamp.valueOf(LocalDateTime.now());
        likes_count = comment_count = 0L;
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
        if(this.likes_count+1 < Long.MAX_VALUE) this.likes_count++;
    }

    public void unlike() {
        if(this.likes_count > 0L) this.likes_count--;
    }

    public void addComment() { if(this.comment_count+1 < Long.MAX_VALUE) this.comment_count++; }
    public void deleteComment() { if(this.comment_count > 0L) this.comment_count--; }
}
