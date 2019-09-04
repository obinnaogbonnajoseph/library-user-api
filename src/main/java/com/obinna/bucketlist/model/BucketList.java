package com.obinna.bucketlist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bucket_list")
@ApiModel(description = "Details of a bucket list")
public class BucketList {

    @ApiModelProperty(notes = "primary key of bucket list entity")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "name of bucket list entity")
    @Column(name = "name", nullable = false)
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
    @Column(name = "date_created", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ApiModelProperty(notes = "date of modification of bucket list")
    @Column(name = "date_modified", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    @ApiModelProperty(notes = "details of user that created bucket list")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
    private User createdBy;

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

    public List<BucketListItem> getItems() {
        return items;
    }

    public void setItems(List<BucketListItem> items) {
        this.items = items;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
