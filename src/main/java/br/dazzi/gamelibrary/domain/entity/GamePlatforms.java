package br.dazzi.gamelibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class GamePlatforms extends EntityDefault{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    @JsonIgnore
    private Library gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platformId")
    @JsonIgnore
    private Platforms platformId;

}
