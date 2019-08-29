package com.obinna.bucketlist.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "user entity that creates bucket list")
@Entity
@Table(name = "user")
public class User {

    @ApiModelProperty(notes = "primary key of user entity")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(notes = "first name of user")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "last name of user")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "email of user")
    @Column(name = "email")
    private String email;

    @ApiModelProperty(notes = "encrypted password of user")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(notes = "bucket list created by the user")
    @OneToOne(mappedBy = "user")
    private BucketList bucketList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BucketList getBucketList() { return bucketList; }

    public void setBucketList(BucketList bucketList) { this.bucketList = bucketList; }
}
