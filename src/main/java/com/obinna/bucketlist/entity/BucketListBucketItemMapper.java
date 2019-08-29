package com.obinna.bucketlist.entity;

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
    private int id;

    @ApiModelProperty(notes = "bucket list id")
    @Column(name = "bucket_list_id")
    private int bucketListId;

    @ApiModelProperty(notes = "bucket list item id")
    @Column(name = "bucket_list_item_id")
    private int bucketItemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBucketListId() {
        return bucketListId;
    }

    public void setBucketListId(int bucketListId) {
        this.bucketListId = bucketListId;
    }

    public int getBucketItemId() {
        return bucketItemId;
    }

    public void setBucketItemId(int bucketItemId) {
        this.bucketItemId = bucketItemId;
    }
}
