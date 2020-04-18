package br.dazzi.gamelibrary.repository.jpql;

import br.dazzi.gamelibrary.domain.entity.Platforms;
import br.dazzi.gamelibrary.repository.PlatformsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;

@Repository
public class PlatformsRepositoryJpql implements PlatformsRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public Platforms add(Platforms entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public void update(Platforms entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void remove(Platforms entity) {
        entityManager.remove(entity);
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

    public Platforms findByPlatform(String platform) {
        return entityManager.createQuery("select p from Platforms p where p.platform = :platform", Platforms.class)
                        .setParameter("platform", platform)
                        .getSingleResult();
    }
}
