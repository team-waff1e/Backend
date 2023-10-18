package io.github.teamwaff1e.waffle.domain.waffle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서의 생성을 열어 둘 필요 ..? x
@Getter
public class Waffle {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer likes;
//    @ManyToOne  // TODO
    private Long memberId;

    @Builder
    public Waffle(Long id, String content, Timestamp createdAt, Timestamp updatedAt, Integer likes, Long memberId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.memberId = memberId;

    }
}
