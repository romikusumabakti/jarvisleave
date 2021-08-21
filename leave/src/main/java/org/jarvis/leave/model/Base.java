package org.jarvis.leave.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class Base {

    @Id
    private Long id;

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

    private Boolean isDeleted;
}
