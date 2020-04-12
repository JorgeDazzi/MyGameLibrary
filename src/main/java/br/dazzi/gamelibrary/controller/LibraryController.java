package br.dazzi.gamelibrary.controller;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.Library;
import br.dazzi.gamelibrary.service.implemented.LibraryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/library")
public class LibraryController {


    @Autowired
    LibraryServiceImp libraryServiceImp;

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<List<Library>> findAll(){


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(path = "/gg")
    public @ResponseBody ResponseEntity<Set<LibraryResponse>> findAllDetailed(){


        return new ResponseEntity<>(libraryServiceImp.getAll(), HttpStatus.OK);
    }


}
