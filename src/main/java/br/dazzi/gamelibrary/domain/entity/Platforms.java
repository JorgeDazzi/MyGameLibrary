package br.dazzi.gamelibrary.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Platforms extends EntityDefault{

    private String platform;

    @OneToMany(mappedBy = "platform_id", fetch = FetchType.LAZY)
    private Set<GamePlatforms> games;
}
