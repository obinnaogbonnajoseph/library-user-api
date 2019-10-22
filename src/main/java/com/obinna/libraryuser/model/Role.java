package com.obinna.libraryuser.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MODIFY_BOOKS,
    CREATE_BOOKS,
    BORROW_BOOKS;

    @Override
    public String getAuthority() {
        return name();
    }
}
