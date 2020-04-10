package br.dazzi.gamelibrary.controller;

import br.dazzi.gamelibrary.controller.response.AboutResponse;
import br.dazzi.gamelibrary.service.AboutServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sys")
public class SysController {


    @Autowired
    private AboutServiceImp aboutRepoImp;

    @GetMapping(path = "/about")
    public @ResponseBody ResponseEntity<AboutResponse> getAbout(){

        AboutResponse about = aboutRepoImp.getAbout();

        return new ResponseEntity<>(about, HttpStatus.OK);
    }

    @GetMapping(path = "/test")
    public @ResponseBody ResponseEntity<String> getTest(){

        return new ResponseEntity<>("OK!", HttpStatus.OK);
    }


}
