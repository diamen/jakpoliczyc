package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> findAll();
    Optional<Tag> findByName(String name);
    List<Tag> in(List<String> names);
    void save(Tag tag);
}
