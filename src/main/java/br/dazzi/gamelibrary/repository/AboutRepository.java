package br.dazzi.gamelibrary.repository;

import br.dazzi.gamelibrary.domain.entity.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository <E extends JpaRepository<About,Long>> {


}
