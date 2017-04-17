package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAll() {
        return entityManager.createQuery("SELECT e FROM COMMENTS e", Comment.class).getResultList();
    }

    @Override
    public void remove(long id) {
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.remove(comment);
    }
}
