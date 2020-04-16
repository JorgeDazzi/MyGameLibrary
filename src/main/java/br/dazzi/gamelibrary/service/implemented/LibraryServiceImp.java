package br.dazzi.gamelibrary.service.implemented;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.domain.exception.NotFoundException;
import br.dazzi.gamelibrary.repository.LibraryRepository;
import br.dazzi.gamelibrary.repository.PlatformsRepository;
import br.dazzi.gamelibrary.repository.jpql.GamePlatformsRepositoryJpql;
import br.dazzi.gamelibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LibraryServiceImp implements LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    GamePlatformsRepositoryJpql gamePlatformsRepositoryJpql;

    @Autowired
    PlatformsRepository platformsRepository;

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
    public void update(Games lib) {

    }

    @Override
    public void remove(Long id) {
        Games game = libraryRepository.find(id);
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

        platformsRepository.findAll().forEach(p -> {
            if(lib.getPlatforms().get(p.getPlatform())){
                gamePlatformsRepositoryJpql.add(new GamePlatforms(games, p));
            }
        } );

        return games;
    }
}
