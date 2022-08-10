package fastcampus.projectboard.repository;

import fastcampus.projectboard.config.JpaConfig;
import fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest
@Import(JpaConfig.class)
@DisplayName("JPA 연결 테스트")
public class JPARepositoryTest {
    @Autowired private ArticleRepository articleRepository;
    @Autowired private ArticleCommentRepository articleCommentRepository;
    public JPARepositoryTest(){//@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository){
        //this.articleRepository=articleRepository;
        //this.articleCommentRepository=articleCommentRepository;
    }
    @DisplayName("Select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles).isNotNull().hasSize(1);
    }
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        //given
        long previousCount = articleRepository.count();
        Article article=Article.of("new article","new content","#spring");
        articleRepository.save(article);
        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles).isNotNull().hasSize(2);
    }
}