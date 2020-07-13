package service;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.ICommentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                throw new BadWordsException("Commenter: " + comment.getCommenter() + " | Detail: " + comment.getDetail() + " | Time: " + timeConvert("dd-MM-yyyy HH:mm:ss"));
            }
        }
        comment.setDate(timeConvert("yyyy-MM-dd"));
        commentRepository.save(comment);
    }

    private String timeConvert(String dateFormat) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(dateFormat);
        return myDateObj.format(myFormatObj);
    }
}
