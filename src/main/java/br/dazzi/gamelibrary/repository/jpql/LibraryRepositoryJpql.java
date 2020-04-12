package br.dazzi.gamelibrary.repository.jpql;

import br.dazzi.gamelibrary.domain.entity.Library;
import br.dazzi.gamelibrary.repository.LibraryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
public class LibraryRepositoryJpql implements LibraryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Library add(Library entity) {
        return null;
    }

    @Override
    public void update(Library entity) {

    }

    @Override
    public void remove(Library entity) {

    }

    @Override
    public Library find(Long id) {
        return entityManager.find(Library.class, id);
    }

    @Override
    public Set<Library> findAll() {
        return Set.copyOf(
                entityManager.createQuery("select l from Library l where 1=1", Library.class)
                        .getResultList()
        );
    }
}
