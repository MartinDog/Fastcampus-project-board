package fastcampus.projectboard.repository;

import fastcampus.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ArticleRepository extends JpaRepository<Article, Long> {

}