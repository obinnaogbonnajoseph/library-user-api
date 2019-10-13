package com.obinna.libraryuser.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "book")
@ApiModel(description = "Details of book")
public class Book extends Auditable {

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
