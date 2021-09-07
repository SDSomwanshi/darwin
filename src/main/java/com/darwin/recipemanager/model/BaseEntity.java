package com.darwin.recipemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Column(name = "CRTE_TS", updatable = false)
    private Timestamp createdDate;

    @Column(name = "UPDT_TS")
    private Timestamp modifiedDate;

    @PreUpdate
    public void onUpdate() {
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }

    @PrePersist
    public void onInsert() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.modifiedDate = this.createdDate;
    }

    @PreRemove
    public void onDelete() {
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }
}
