package org.jarvis.leave.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
