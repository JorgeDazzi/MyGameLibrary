package br.dazzi.gamelibrary.domain.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Games extends EntityDefault{

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

    @OneToMany(mappedBy = "gameId", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<GamePlatforms> platforms = new ArrayList<>();
}