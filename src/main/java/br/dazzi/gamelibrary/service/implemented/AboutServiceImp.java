package br.dazzi.gamelibrary.service.implemented;

import br.dazzi.gamelibrary.controller.response.AboutResponse;
import br.dazzi.gamelibrary.domain.entity.About;
import br.dazzi.gamelibrary.repository.AboutRepositoryImp;
import br.dazzi.gamelibrary.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutServiceImp implements AboutService {


    @Autowired
    private AboutRepositoryImp aboutRepo;

    @Override
    public AboutResponse getAbout() {
        About about = aboutRepo.getAbout();
        return new AboutResponse(about);
    }

    @Override
    public void update(About about) {

    }

    @Override
    public AboutResponse add(About about) {
        return null;
    }
}
