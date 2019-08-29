package com.obinna.bucketlist.dto;

import java.sql.Timestamp;
import java.util.List;

public class BucketListDto {

    private int id;

    private String name;

    private Timestamp dateCreated;

    private Timestamp dateModified;

    private List<BucketListItemDto> items;

    private int createdBy;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public List<BucketListItemDto> getItems() {
        return items;
    }

    public void setItems(List<BucketListItemDto> items) {
        this.items = items;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}