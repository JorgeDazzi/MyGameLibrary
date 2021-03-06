package br.dazzi.gamelibrary.repository.jpql;

import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.domain.entity.Platforms;
import br.dazzi.gamelibrary.repository.GamePlatformsRepository;
import br.dazzi.gamelibrary.repository.PlatformsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Set;

@Repository
public class GamePlatformsRepositoryJpql implements GamePlatformsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PlatformsRepository platformsRepository;


    @Override
    @Transactional
    public GamePlatforms add(GamePlatforms entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public void update(GamePlatforms entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void remove(GamePlatforms entity) {
        entityManager.remove(entity);
    }

    @Override
    public GamePlatforms find(Long id) {
        return entityManager.find(GamePlatforms.class, id);
    }

    @Override
    public Set<GamePlatforms> findAll() {
        return Set.copyOf(
                entityManager.createQuery("select g from GamePlatforms g where 1=1", GamePlatforms.class)
                        .getResultList()
        );
    }

    public boolean isThisPlatformSupported(Long gameId, Long platformId){
        Long query =
                (Long) entityManager
                .createQuery("select count(g) from GamePlatforms g where g.gameId.id = :gameId and g.platformId.id = :platformId")
                .setParameter("gameId", gameId)
                .setParameter("platformId", platformId).getSingleResult();

        return query > 0;
    }

    public HashMap<String, Boolean> gamePlatformSupported(Long gameId){

        HashMap<String,Boolean> platforms = new HashMap<>();

        platformsRepository.findAll().forEach(p -> {
            platforms.put(
                    p.getPlatform(),
                    this.isThisPlatformSupported(gameId, p.getId())
            );
        });

        return platforms;
    }


    @Transactional
    public void remove(Platforms platforms, Games game) {
        GamePlatforms gp;

        try{
             gp = entityManager.createQuery("select gp from GamePlatforms gp where gp.gameId.id = :game and gp.platformId.id = :platform", GamePlatforms.class)
                    .setParameter("game", game.getId())
                    .setParameter("platform", platforms.getId())
                    .getSingleResult();
        }catch (NoResultException e){
             gp = null;
        }

        if(gp != null) entityManager.remove(gp);
    }


}
