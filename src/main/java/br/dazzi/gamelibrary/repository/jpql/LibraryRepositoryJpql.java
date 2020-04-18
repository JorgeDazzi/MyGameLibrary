package br.dazzi.gamelibrary.repository.jpql;

import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.repository.LibraryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;

@Repository
public class LibraryRepositoryJpql implements LibraryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Games add(Games entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public void update(Games entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void remove(Games entity) {
        entityManager.remove(entity);
    }

    @Override
    public Games find(Long id) {
        return entityManager.find(Games.class, id);
    }

    @Override
    public Set<Games> findAll() {
        return Set.copyOf(
                entityManager.createQuery("select l from Games l where 1=1", Games.class)
                        .getResultList()
        );
    }
}
