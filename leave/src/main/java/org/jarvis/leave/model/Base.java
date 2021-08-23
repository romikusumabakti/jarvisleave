package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdDate;

    @Column(length = 50)
    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @Column(columnDefinition = "bool default false")
    private Boolean isDeleted = false;
}
