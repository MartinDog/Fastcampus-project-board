package fastcampus.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fastcampus.projectboard.domain.ArticleComment;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
}
