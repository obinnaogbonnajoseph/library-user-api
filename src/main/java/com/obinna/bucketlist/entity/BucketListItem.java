package com.obinna.bucketlist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@ApiModel(description = "Bucket list item entity")
@Table(name = "bucket_list_item")
public class BucketListItem {

    @ApiModelProperty(notes = "primary key of bucket list item")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(notes = "name of bucket list item")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(notes = "date of creation of bucket list item")
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Timestamp dateCreated;

    @ApiModelProperty(notes = "last date of modification of bucket list item")
    @Column(name = "date_modified")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateModified;

    @ApiModelProperty(notes = "boolean to show if item is done or not")
    @Column(name = "done")
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
