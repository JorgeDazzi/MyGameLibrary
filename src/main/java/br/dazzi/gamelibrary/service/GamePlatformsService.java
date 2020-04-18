package br.dazzi.gamelibrary.service;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.GamePlatforms;
import br.dazzi.gamelibrary.domain.entity.Games;

import java.util.Set;


public interface GamePlatformsService {

    public Set<LibraryResponse> getAll();

    public LibraryResponse find(Long id);

    public void update(GamePlatforms gamePlatforms);

    public void remove(Long gamePlatforms);

    public Games add(GamePlatforms gamePlatforms);
}
