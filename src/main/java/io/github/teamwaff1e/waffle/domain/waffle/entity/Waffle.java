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
    private Long likes;  // TODO 엔티티로 분리?

//    @ManyToOne  // TODO
//    @JoinColumn(name = "member_id")
    private Long memberId;

    @Builder
    protected Waffle(Long id, String content, Long likes, Long memberId) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.memberId = memberId;

    }

    @PrePersist
    public void prePersist() {
        updatedAt = createdAt = Timestamp.valueOf(LocalDateTime.now());
        likes = 0L;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void updateWaffleContent(String content) {
        this.content = content;
    }

    public void like() {
        if(this.likes+1 < Integer.MAX_VALUE) this.likes++;
    }

    public void unlike() {
        if(this.likes > 0) this.likes--;
    }
}
