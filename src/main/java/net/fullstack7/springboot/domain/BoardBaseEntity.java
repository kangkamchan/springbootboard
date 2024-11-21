package net.fullstack7.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BoardBaseEntity {
    @CreatedDate
    @Column(name="regDate", updatable=false)
    private LocalDateTime regDate;
    @Setter
    @LastModifiedDate
    @Column(name="modifyDate", insertable = false, updatable=true)
    private LocalDateTime modifyDate;

    public void setModifyDate() {
        this.modifyDate = LocalDateTime.now();
    }
}
