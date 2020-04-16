package br.dazzi.gamelibrary.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse {

    private Long id;

    @NotNull(message = "Game.name field cannot be empty")
    @NotEmpty(message = "Game.name field cannot be empty")
    private String name;

    private Long steamAppId;

    private int required_age;

    private boolean free;

    @Lob
    private String desc;

    private String website;

    @NotNull(message = "Game.dev field cannot be empty")
    @NotEmpty(message = "Game.dev field cannot be empty")
    private String dev;

    @NotNull(message = "Game.publishers field cannot be empty")
    @NotEmpty(message = "Game.publishers field cannot be empty")
    private String publishers;

    private HashMap<String, Boolean> platforms;

}
