package pl.jakpoliczyc.dao.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.web.dto.ArticleCompressedDto;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.util.List;

public interface ArticleService {
    void save(StoryMenuTagDto wrapper);
    void update(long articleId, StoryMenuTagDto wrapper);
    void delete(long articleId);
    void save(long articleId, CommentDto wrapper);
    void delete(long articleId, long commentId);
    Article find(long id);
    Page<ArticleCompressedDto> findAll(final Pageable pageable);
    Page<ArticleCompressedDto> findByMenuId(final Pageable pageable, final long menuId);
    Page<ArticleCompressedDto> findByTagId(final Pageable pageable, final List<Long> ids);
}
