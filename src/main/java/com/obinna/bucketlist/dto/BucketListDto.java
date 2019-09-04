package com.obinna.bucketlist.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BucketListDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Date dateCreated;

    @NotNull
    private Date dateModified;

    private List<BucketListItemDto> items;

    @NotNull
    private Long createdBy;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public List<BucketListItemDto> getItems() {
        return items;
    }

    public void setItems(List<BucketListItemDto> items) {
        this.items = items;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}