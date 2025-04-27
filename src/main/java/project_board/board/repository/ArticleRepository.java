package project_board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project_board.board.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}