package project_board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project_board.board.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}