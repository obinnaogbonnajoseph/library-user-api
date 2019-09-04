package com.obinna.bucketlist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "entity that maps bucket list items to a bucket list")
@Entity
@Table(name = "bucket_list_bucket_items")
public class BucketListBucketItemMapper {

    @ApiModelProperty(notes = "primary key of bucket list - bucket items mapper")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "bucket list id")
    @Column(name = "bucket_list_id")
    private Long bucketListId;

    @ApiModelProperty(notes = "bucket list item id")
    @Column(name = "bucket_list_item_id")
    private Long bucketItemId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBucketListId() {
        return bucketListId;
    }

    public void setBucketListId(Long bucketListId) {
        this.bucketListId = bucketListId;
    }

    public Long getBucketItemId() {
        return bucketItemId;
    }

    public void setBucketItemId(Long bucketItemId) {
        this.bucketItemId = bucketItemId;
    }
}
