package fastcampus.projectboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import fastcampus.projectboard.domain.QArticle;
import fastcampus.projectboard.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import fastcampus.projectboard.domain.ArticleComment;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override//QuerydslBinderCustomizer에서 QuerydslPredicateExecutor를통한 검색 설정
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);//검색범위를 모두로 할지 아니면 특정 값으로 할지 설정 default = false
        bindings.including(root.createdAt,root.createdBy,root.content);//id를 통한 검색 제거

        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);//title로 검색할 때 어떻게 비교할 지 확인 여기서는 검색시 완전일치가 아닌 포함 및 대소문문자 구분 x으로 바꾸어줬다 기본은 완전일치
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);//StringExpression::containsIgnoreCase <-검색시 다르게 나옴
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }
}
