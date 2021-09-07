package org.jarvis.leave.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Holiday extends Base {
    private String name;
    private String description;
    private LocalDate date;
}
