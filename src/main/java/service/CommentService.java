package service;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.ICommentRepository;

import java.util.Arrays;
import java.util.List;

public class CommentService implements ICommentService{
    private List<String> badWords = Arrays.asList("fuck", "shit", "darn", "penis", "pussy");

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void save(Comment comment) throws BadWordsException {
        for (String word: badWords) {
            if (comment.getDetail().contains(word)) {
                throw new BadWordsException();
            }
        }
        commentRepository.save(comment);
    }
}
