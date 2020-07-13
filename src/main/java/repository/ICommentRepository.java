package repository;

import model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
