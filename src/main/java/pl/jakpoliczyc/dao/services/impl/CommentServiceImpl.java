package pl.jakpoliczyc.dao.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Comment;
import pl.jakpoliczyc.dao.repos.CommentRepository;
import pl.jakpoliczyc.dao.services.CommentService;

import java.util.List;

@Service("commentServiceImpl")
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
