package fastcampus.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fastcampus.projectboard.domain.ArticleComment;
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
}
