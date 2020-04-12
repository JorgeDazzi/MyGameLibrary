package br.dazzi.gamelibrary.repository;

import br.dazzi.gamelibrary.domain.entity.EntityDefault;

import java.util.Set;

public interface Repository <E extends EntityDefault> {

    E add(E entity);

    void update(E entity);

    void remove(E entity);

    E find(Long id);

    Set<E> findAll();

}
