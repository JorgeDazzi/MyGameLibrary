package br.dazzi.gamelibrary.controller.response;

import br.dazzi.gamelibrary.domain.entity.Platforms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse {

    private Long id;

    private String name;

    private Long steamAppId;

    private int required_age;

    private boolean free;

    @Lob
    private String desc;

    private String website;

    private String dev;

    private String publishers;

    private HashMap<String, Boolean> platforms;

}
