package controller;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BadWordsException;
import service.ICommentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping
    public ModelAndView showComments(@PageableDefault(size = 3, direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/index");
        Page<Comment> comments = commentService.findAll(pageable);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @GetMapping("/comment-like/{id}")
    public String like(@PathVariable Long id) throws BadWordsException {
        Comment comment = commentService.findById(id);
        comment.setUpVote(comment.getUpVote() + 1);
        commentService.save(comment);
        return "redirect:/comments";
    }

    @PostMapping("/comment-save")
    public String save(Comment comment) throws BadWordsException {
        commentService.save(comment);
        return "redirect:/comments";
    }

    @ExceptionHandler(BadWordsException.class) 
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/badWordNotAcceptable");
    }

}
