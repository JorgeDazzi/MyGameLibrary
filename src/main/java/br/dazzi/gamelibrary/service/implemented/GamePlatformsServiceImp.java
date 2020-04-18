package br.dazzi.gamelibrary.service.implemented;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.repository.jpql.GamePlatformsRepositoryJpql;
import br.dazzi.gamelibrary.repository.jpql.PlatformsRepositoryJpql;
import br.dazzi.gamelibrary.service.GamePlatformsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GamePlatformsServiceImp implements GamePlatformsService {

    @Autowired
    GamePlatformsRepositoryJpql gamePlatformsRepositoryJpql;

    @Autowired
    PlatformsRepositoryJpql platformsRepositoryJpql;



    @Override
    public Set<LibraryResponse> getAll() {
        return null;
    }

    @Override
    public LibraryResponse find(Long id) {
        return null;
    }

    @Override
    public void update(GamePlatforms gamePlatforms) {

    }

    @Override
    public void remove(Long gamePlatforms) {

    }

    @Override
    public Games add(GamePlatforms gamePlatforms) {
        return null;
    }



}
