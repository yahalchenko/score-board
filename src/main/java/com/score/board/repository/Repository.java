package com.score.board.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<ID, T> {
    <S extends T> S save(S entity);

    <U extends T> U update(U entity);

    Optional<T> getById(ID id);

    List<T> getAll();

    void deleteById(ID id);
}
