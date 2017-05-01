package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Stag;

import java.util.List;
import java.util.Optional;

public interface StagRepository {
    List<Stag> findAll();
    Optional<Stag> findByName(String name);
    List<Stag> in(List<String> names);
    void save(Stag stag);
}
