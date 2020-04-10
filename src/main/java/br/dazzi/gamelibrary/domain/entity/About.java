package br.dazzi.gamelibrary.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "api_about") @Table(name = "api_about")
public class About extends EntityDefault{

    @Column(name = "version_num",length = 10)
    private String versionNum;

    @Column(name = "version_name",length = 20)
    private String versionName;
}
