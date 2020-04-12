package br.dazzi.gamelibrary.repository.jpql;

import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.repository.GamePlatformsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Set;

@Repository
public class GamePlatformsRepositoryJpql implements GamePlatformsRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public GamePlatforms add(GamePlatforms entity) {
        return null;
    }

    @Override
    public void update(GamePlatforms entity) {

    }

    @Override
    public void remove(GamePlatforms entity) {

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
        /*
            select
                p.platform,
                casewhen((select count(1) from GAME_PLATFORMS g where g.game_id = 1 and g.platform_id = p.id)> 0, true, false)
            from PLATFORMS p where 1=1
         */
        //Set<Platforms> platforms

        return null;
    }
}
