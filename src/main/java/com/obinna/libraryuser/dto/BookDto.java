package com.obinna.libraryuser.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BookDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotBlank
    private String libNumber;

    @NotBlank
    private String status;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLibNumber() {
        return libNumber;
    }

    public void setLibNumber(String libNumber) {
        this.libNumber = libNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}