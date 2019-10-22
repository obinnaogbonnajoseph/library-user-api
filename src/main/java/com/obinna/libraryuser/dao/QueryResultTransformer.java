package com.obinna.libraryuser.dao;

public interface QueryResultTransformer<E, T> {

    T transform(E e);
}
