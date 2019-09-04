package com.obinna.bucketlist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@ApiModel(description = "Bucket list item entity")
@Table(name = "bucket_list_item")
public class BucketListItem {

    @ApiModelProperty(notes = "primary key of bucket list item")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "name of bucket list item")
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(notes = "date of creation of bucket list item")
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dateCreated;

    @ApiModelProperty(notes = "last date of modification of bucket list item")
    @Column(name = "date_modified", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    @ApiModelProperty(notes = "boolean to show if item is done or not")
    @Column(name = "done")
    private boolean done;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
