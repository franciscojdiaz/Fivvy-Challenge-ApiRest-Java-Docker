package com.challenge.fivvy.dominio;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class AuditableBase2 {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Timestamp createAt;

}
