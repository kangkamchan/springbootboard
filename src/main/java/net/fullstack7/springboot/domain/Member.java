package net.fullstack7.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tbl_board_member")
public class Member {
    @Id
    @Column(unique = true, nullable = false, length=20, columnDefinition = "VARCHAR(20)")
    private String memberId;
    @Column(length = 20, nullable = false)
    private String pwd;
}
