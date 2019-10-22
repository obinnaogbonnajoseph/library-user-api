package com.obinna.libraryuser.dao;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;
import java.util.Optional;


public interface AppRepository {

    <E> long count(Class<E> type);

    <E> Optional<E> findById(Class<E> type, Object id);

    <E> E persist(E e);

    <E> E merge(E e);

    void remove(Object e);


    <E> JPAQuery<E> startJPAQuery(EntityPath<E> entityPath);

    <E> QueryResults<E> fetchResults(JPAQuery<E> query);

    <E> List<E> fetchResultList(JPAQuery<E> query);

    <E> Optional<E> fetchOne(JPAQuery<E> query);

    <E, T> QueryResults<T> fetchResults(JPAQuery<E> query,QueryResultTransformer<E, T> transformer);
}
