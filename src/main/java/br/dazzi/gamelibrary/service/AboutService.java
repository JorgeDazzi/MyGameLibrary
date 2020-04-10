package br.dazzi.gamelibrary.service;

import br.dazzi.gamelibrary.controller.response.AboutResponse;
import br.dazzi.gamelibrary.domain.entity.About;

public interface AboutService {

    public AboutResponse getAbout();

    public void update(About about);

    public AboutResponse add(About about);

}
