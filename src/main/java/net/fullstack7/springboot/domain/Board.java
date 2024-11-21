package net.fullstack7.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tbl_board")
public class Board extends BoardBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idx;
    @Column(nullable = false, length = 20)
    private String memberId;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 2000,columnDefinition = "text")
    private String content;
    @Column(nullable = true, length = 10)
    private String displayDate;

    public void modify(String memberId, String title, String content, String displayDate){
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.displayDate = displayDate;
        super.setModifyDate();
    }

    public void modify(String memberId, String title, String content, String displayDate, LocalDateTime modifyDate){
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.displayDate = displayDate;
        super.setModifyDate(modifyDate);
    }
}
