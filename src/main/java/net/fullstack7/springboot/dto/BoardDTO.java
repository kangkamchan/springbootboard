package net.fullstack7.springboot.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private int idx;
    private String memberId;
    private String title;
    private String content;
    private String displayDate;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;
}
