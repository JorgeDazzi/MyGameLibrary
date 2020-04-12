package br.dazzi.gamelibrary.service;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.Library;

import java.util.Set;


public interface LibraryService {

    public Set<LibraryResponse> getAll();

    public Library find(Long id);

    public void update(Library lib);

    public void remove(Library lib);

    public Library add(Library lib);
}
