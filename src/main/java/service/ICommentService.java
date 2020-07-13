package service;

import model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentService {
    Page<Comment> findAll(Pageable pageable);

    Comment findById(Long id);

    void save(Comment comment) throws BadWordsException;
}
