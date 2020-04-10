package br.dazzi.gamelibrary.domain.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class EntityDefault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
