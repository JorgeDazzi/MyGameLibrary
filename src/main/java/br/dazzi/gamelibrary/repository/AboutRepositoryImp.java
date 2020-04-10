package br.dazzi.gamelibrary.repository;

import br.dazzi.gamelibrary.domain.entity.About;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AboutRepositoryImp implements AboutRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public About getAbout(){
        return entityManager
                .createQuery("SELECT a FROM api_about a where 1=1 order by a.id desc", About.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}
