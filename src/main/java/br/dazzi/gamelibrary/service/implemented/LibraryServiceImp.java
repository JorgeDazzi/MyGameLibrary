package br.dazzi.gamelibrary.service.implemented;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.controller.response.converter.GameConverter;
import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.domain.entity.Platforms;
import br.dazzi.gamelibrary.domain.exception.NotFoundException;
import br.dazzi.gamelibrary.repository.LibraryRepository;
import br.dazzi.gamelibrary.repository.jpql.GamePlatformsRepositoryJpql;
import br.dazzi.gamelibrary.repository.jpql.PlatformsRepositoryJpql;
import br.dazzi.gamelibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryServiceImp implements LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    GamePlatformsRepositoryJpql gamePlatformsRepositoryJpql;

    @Autowired
    PlatformsRepositoryJpql platformsRepositoryJpql;

    @Override
    public Set<LibraryResponse> getAll() {

        Set<LibraryResponse> result = new HashSet<>();

        libraryRepository.findAll().forEach(g -> result.add(
          new LibraryResponse(
                g.getId(),
                g.getName(),
                g.getSteamAppId(),
                g.getRequired_age(),
                g.isFree(),
                g.getDesc(),
                g.getWebsite(),
                g.getDev(),
                g.getPublishers(),
                this.gamePlatformsRepositoryJpql.gamePlatformSupported( g.getId() )
          )
        ));
        return result;
    }

    @Override
    public LibraryResponse find(Long id) {
        Games lib = libraryRepository.find(id);

        if(lib == null){
            throw new NotFoundException("The Game was not found");
        }

        return new LibraryResponse(
                lib.getId(),
                lib.getName(),
                lib.getSteamAppId(),
                lib.getRequired_age(),
                lib.isFree(),
                lib.getDesc(),
                lib.getWebsite(),
                lib.getDev(),
                lib.getPublishers(),
                this.gamePlatformsRepositoryJpql.gamePlatformSupported( lib.getId() )
        );
    }

    @Override
    public void update(LibraryResponse lib) {
        Games game = libraryRepository.find(lib.getId());
        if(game == null){
            throw new NotFoundException("Game was not found");
        }

        libraryRepository.update(
                GameConverter.converterToGame(
                        lib,
                        List.copyOf(platformsRepositoryJpql.findAll())
                )
        );
        this.rebuildGameVsPlatforms(libraryRepository.find(lib.getId()), lib.getPlatforms());
    }

    @Override
    public void remove(Long id) {
        Games game = libraryRepository.find(id);
        if(game == null){
            throw new NotFoundException("Game was not found or already gone");
        }
        libraryRepository.remove(game);
    }

    @Override
    public Games add(LibraryResponse lib) {

        Games games = new Games(
                lib.getName(),
                lib.getSteamAppId(),
                lib.getRequired_age(),
                lib.isFree(),
                lib.getDesc(),
                lib.getWebsite(),
                lib.getDev(),
                lib.getPublishers(),
                null
        );
        libraryRepository.add(games);

        platformsRepositoryJpql.findAll().forEach(p -> {
            if(lib.getPlatforms().get(p.getPlatform())){
                gamePlatformsRepositoryJpql.add(new GamePlatforms(games, p));
            }
        } );

        return games;
    }

    public void rebuildGameVsPlatforms(Games game, HashMap<String, Boolean> platformsJson){
        if(platformsJson != null) {
            platformsJson.forEach((platform, value) -> {
                Platforms platformEntity = platformsRepositoryJpql.findByPlatform(platform);
                if (value) {
                    if (!gamePlatformsRepositoryJpql
                            .isThisPlatformSupported(
                                    game.getId(),
                                    platformEntity.getId()
                            )
                    ) {
                        gamePlatformsRepositoryJpql.add(new GamePlatforms(game, platformEntity));
                    }
                } else {
                    gamePlatformsRepositoryJpql.remove(platformEntity, game);
                }
            });
        }
    }
}
