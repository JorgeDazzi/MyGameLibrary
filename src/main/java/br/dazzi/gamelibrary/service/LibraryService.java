package br.dazzi.gamelibrary.service;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.Games;

import java.util.Set;


public interface LibraryService {

    public Set<LibraryResponse> getAll();

    public LibraryResponse find(Long id);

    public void update(Games lib);

    public void remove(Long lib);

    public Games add(LibraryResponse lib);
}
