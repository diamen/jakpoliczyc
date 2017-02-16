package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();
}
