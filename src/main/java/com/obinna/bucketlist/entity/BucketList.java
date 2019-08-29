package com.obinna.bucketlist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bucket_list")
@ApiModel(description = "Details of a bucket list")
public class BucketList {

    @ApiModelProperty(notes = "primary key of bucket list entity")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(notes = "name of bucket list entity")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(notes = "list of items attached to a particular bucket list")
    @OneToMany
    @JoinTable(
            name="bucket_list_bucket_items",
            joinColumns = @JoinColumn( name="bucket_list_id"),
            inverseJoinColumns = @JoinColumn( name="bucket_list_item_id")
    )
    private List<BucketListItem> items;

    @ApiModelProperty(notes = "date of creation of bucket list")
    @Column(name = "date_created")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateCreated;

    @ApiModelProperty(notes = "date of modification of bucket list")
    @Column(name = "date_modified")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateModified;

    @ApiModelProperty(notes = "details of user that created bucket list")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_fk")
    private User createdBy;

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

    public List<BucketListItem> getItems() {
        return items;
    }

    public void setItems(List<BucketListItem> items) {
        this.items = items;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
