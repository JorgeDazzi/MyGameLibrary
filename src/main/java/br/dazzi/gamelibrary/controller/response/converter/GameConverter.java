package br.dazzi.gamelibrary.controller.response.converter;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.domain.entity.Platforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameConverter {

    public static List<GamePlatforms> convertToListGamePlatforms(Games game, List<Platforms> platforms, HashMap<String, Boolean> jsonPlatforms){
        List<GamePlatforms> gp = new ArrayList<>();

        jsonPlatforms.forEach((p,value)->{
            if(value){
                gp.add(new GamePlatforms(
                        game,
                        platforms.stream().filter(
                                streamPlatform-> streamPlatform.getPlatform().equals(p)).collect(Collectors.toList()).get(0)
                ));
            }
        });
        return gp;
    }

    public static Games converterToGame(LibraryResponse libraryResponse, List<Platforms> platforms) {

        Games g = new Games(
                libraryResponse.getId(),
                libraryResponse.getName(),
                libraryResponse.getSteamAppId(),
                libraryResponse.getRequired_age(),
                libraryResponse.isFree(),
                libraryResponse.getDesc(),
                libraryResponse.getWebsite(),
                libraryResponse.getDev(),
                libraryResponse.getPublishers(),
                null
        );
        g.setPlatforms(
                GameConverter.convertToListGamePlatforms(g, platforms, libraryResponse.getPlatforms())
        );

        return g;
    }

}
