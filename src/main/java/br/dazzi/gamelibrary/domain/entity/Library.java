package br.dazzi.gamelibrary.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Library extends EntityDefault{
    
    @NotNull @NotEmpty
    private String name;

    private Long steamAppId;

    private int required_age;

    private boolean free;

    @Lob private String desc;

    private String website;

    @NotNull @NotEmpty
    private String dev;

    @NotNull @NotEmpty
    private String publishers;

    @OneToMany(mappedBy = "game_id", fetch = FetchType.LAZY)
    private Set<GamePlatforms> platforms;
}
