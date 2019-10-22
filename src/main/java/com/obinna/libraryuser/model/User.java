package com.obinna.libraryuser.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@ApiModel(description = "user entity that creates bucket list")
@Entity
@Table(name = "library_user")
public class User extends Auditable {

    @ApiModelProperty(notes = "primary key of user entity")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "first name of user")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "last name of user")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "username of user")
    @Column(name = "username", nullable = false)
    private String username;

    @ApiModelProperty(notes = "encrypted password of user")
    @Column(name = "password", nullable = false)
    private String password;

    @ApiModelProperty(notes = "roles assigned to user")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
