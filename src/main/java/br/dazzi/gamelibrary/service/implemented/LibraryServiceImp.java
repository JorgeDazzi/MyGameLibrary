package br.dazzi.gamelibrary.service.implemented;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.Library;
import br.dazzi.gamelibrary.repository.LibraryRepository;
import br.dazzi.gamelibrary.repository.PlatformsRepository;
import br.dazzi.gamelibrary.repository.jpql.GamePlatformsRepositoryJpql;
import br.dazzi.gamelibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class LibraryServiceImp implements LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    PlatformsRepository platformsRepository;

    @Autowired
    GamePlatformsRepositoryJpql gamePlatformsRepositoryJpql;

    @Override
    public Set<LibraryResponse> getAll() {

        Set<LibraryResponse> result = new HashSet<>();

        libraryRepository.findAll().forEach(g -> {
            HashMap<String,Boolean> platforms = new HashMap<>();

            platformsRepository.findAll().forEach( p -> {
                platforms.put(
                        p.getPlatform(),
                        gamePlatformsRepositoryJpql.isThisPlatformSupported(g.getId(), p.getId())
                );
            });

            result.add(
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
                    platforms
                )
            );
        });
        return result;
    }

    @Override
    public Library find(Long id) {
        return null;
    }

    @Override
    public void update(Library lib) {

    }

    @Override
    public void remove(Library lib) {

    }

    @Override
    public Library add(Library lib) {
        return null;
    }
}
