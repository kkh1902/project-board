package project_board.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import project_board.board.config.JpaConfig;
import project_board.board.domain.Article;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.test.database.replace=none")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_theWorksFine(){
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(10);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_theWorksFine(){
        // Given
        long previousCount = articleRepository.count();
        // When
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("Update 테스트")
    @Test
    void givenTestData_whenUpdating_theWorksFine(){
        // Given
        Article article = articleRepository.findById(2L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setContent(updatedHashtag);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("content", updatedHashtag);

    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_theWorksFine(){
        // Given
        Article article = articleRepository.findById(2L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        long deletedCommentSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);

    }
}