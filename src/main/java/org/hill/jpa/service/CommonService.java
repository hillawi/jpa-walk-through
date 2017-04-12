package org.hill.jpa.service;

import org.hill.jpa.model.PaginatedListWrapper;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Hillawi on 12-04-17.
 */
public interface CommonService<T> {
    T create(T t);

    T get(long id);

    T update(T t);

    List<T> getAll();

    PaginatedListWrapper<T> get(PaginatedListWrapper<T> listWrapper);

    default List<T> create(List<T> list) {
        list.parallelStream().forEach(t -> create(t));
        return list;
    }

    default List<T> find(int startPosition, int maxResult, Query query) {
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    default Integer count(Query query) {
        return ((Long) query.getSingleResult()).intValue();
    }
}
