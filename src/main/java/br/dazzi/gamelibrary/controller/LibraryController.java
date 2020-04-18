package br.dazzi.gamelibrary.controller;

import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import br.dazzi.gamelibrary.domain.entity.Games;
import br.dazzi.gamelibrary.service.implemented.LibraryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/library")
@CrossOrigin(value = "*")
public class LibraryController {


    @Autowired
    LibraryServiceImp libraryServiceImp;


    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Set<LibraryResponse>> findAllDetailed(){


        return new ResponseEntity<>(libraryServiceImp.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}" )
    public @ResponseBody ResponseEntity<LibraryResponse> findById(@PathVariable Long id){

        return new ResponseEntity<>(libraryServiceImp.find(id), HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<LibraryResponse> addGame(@RequestBody @Validated LibraryResponse libraryResponse){

        Games games = libraryServiceImp.add(libraryResponse);
        return new ResponseEntity<>(libraryServiceImp.find(games.getId()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}" )
    public @ResponseBody ResponseEntity<Object> remove(@PathVariable Long id){
        libraryServiceImp.remove(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> update(@RequestBody @Validated LibraryResponse libraryResponse){
        libraryServiceImp.update(libraryResponse);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
