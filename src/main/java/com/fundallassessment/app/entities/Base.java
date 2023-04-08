package com.fundallassessment.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public abstract class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_updated")
    private LocalDateTime updatedAt;


    @PrePersist
    public void createdAt(){

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updatedAt(){

        this.updatedAt = LocalDateTime.now();
    }

}
