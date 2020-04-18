package br.dazzi.gamelibrary.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Games extends EntityDefault{

    @NotBlank
    private String name;

    @Column(unique = true)
    private Long steamAppId;

    private int required_age;

    private boolean free;

    @Lob private String desc;

    private String website;

    @NotBlank
    private String dev;

    @NotBlank
    private String publishers;

    @OneToMany(mappedBy = "gameId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<GamePlatforms> platforms = new ArrayList<>();

    public Games(Long id,@NotBlank String name, Long steamAppId, int required_age, boolean free, String desc, String website, @NotBlank String dev, @NotBlank String publishers, List<GamePlatforms> platforms) {
        this.name = name;
        this.steamAppId = steamAppId;
        this.required_age = required_age;
        this.free = free;
        this.desc = desc;
        this.website = website;
        this.dev = dev;
        this.publishers = publishers;
        this.platforms = platforms;
        this.setId(id);
    }
}
