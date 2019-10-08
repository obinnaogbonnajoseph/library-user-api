package com.obinna.libraryuser.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bucket_list")
@ApiModel(description = "Details of a bucket list")
public class Book {

    @ApiModelProperty(notes = "primary key of book entity")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "name of book entity")
    @Column(name = "name", nullable = false)
    private String name;

    @ApiModelProperty(notes = "author of book entity")
    @Column(name = "author", nullable = false)
    private String author;

    @ApiModelProperty(notes = "library number of book entity")
    @Column(name = "lib_number", nullable = false)
    private String libNumber;

    @ApiModelProperty(notes = "status of book entity")
    @Column(name = "status", nullable = false)
    private String status;


    @ApiModelProperty(notes = "date of creation of bucket list")
    @Column(name = "date_created", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

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

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getLibNumber() { return libNumber; }

    public void setLibNumber(String libNumber) { this.libNumber = libNumber; }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
