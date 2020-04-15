package br.dazzi.gamelibrary.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class GamePlatforms extends EntityDefault{

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gameId")
    @JsonIgnore
    private Games gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platformId")
    @JsonIgnore
    private Platforms platformId;

}
