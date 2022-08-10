package fastcampus.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fastcampus.projectboard.domain.ArticleComment;
import org.springframework.stereotype.Repository;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
}
