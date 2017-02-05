package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("commentServiceImpl")
@Transactional
public class CommentServiceImpl implements CommentService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Comment> findAll() {
        return entityManager.createQuery("SELECT e FROM COMMENTS e", Comment.class).getResultList();
    }

    public void remove(long id) {
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.remove(comment);
    }
}
