package fastcampus.projectboard;

import fastcampus.projectboard.domain.Article;
import fastcampus.projectboard.repository.ArticleCommentRepository;
import fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import fastcampus.projectboard.config.JpaConfig;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@Disabled
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)//해당 클래스를 사용한다.
@DataJpaTest
class JPAApplicationTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JPAApplicationTest(@Autowired ArticleRepository articleRepository,@Autowired  ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @Test
    @DisplayName("Select Test")
    void givenTestData_whenSelecting_thenWorkisfine(){
        //given
        long previousCount = articleRepository.count();
        Article savedarticle = Article.of("new Article","new content","#spring");
        articleRepository.save(savedarticle);
        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);

    }
    @Test
    @DisplayName("Update Test")
    void givenTestData_whenUpdateing_thenWorkisfine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousCount = articleRepository.count();
        String updatedHashtag="#srpingboot";
        article.setHashtag(updatedHashtag);
        //when
        Article savedarticles = articleRepository.saveAndFlush(article);

        //then
        assertThat(savedarticles).hasFieldOrPropertyWithValue("hashtag",updatedHashtag);
    }
    @Test
    @DisplayName("Delete Test")
    void givenTestData_whenDelete_thenWorkisfine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();//@Getter를 통해 만들어진 getter
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentsSize);
    }
}
