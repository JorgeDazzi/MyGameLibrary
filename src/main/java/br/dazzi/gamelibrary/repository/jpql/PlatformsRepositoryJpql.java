package br.dazzi.gamelibrary.repository.jpql;

import br.dazzi.gamelibrary.domain.entity.Platforms;
import br.dazzi.gamelibrary.repository.PlatformsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
public class PlatformsRepositoryJpql implements PlatformsRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Platforms add(Platforms entity) {
        return null;
    }

    @Override
    public void update(Platforms entity) {

    }

    @Override
    public void remove(Platforms entity) {

    }

    @Override
    public Platforms find(Long id) {
        return entityManager.find(Platforms.class, id);
    }

    @Override
    public Set<Platforms> findAll() {
        return Set.copyOf(
                entityManager.createQuery("select p from Platforms p where 1=1", Platforms.class)
                        .getResultList()
        );
    }
}
