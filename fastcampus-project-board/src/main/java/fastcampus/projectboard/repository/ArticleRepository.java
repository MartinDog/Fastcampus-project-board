package fastcampus.projectboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import fastcampus.projectboard.domain.Article;
import fastcampus.projectboard.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/* 조회기능 추가
public interface QuerydslPredicateExecutor<T> {

  Optional<T> findById(Predicate predicate);  

  Iterable<T> findAll(Predicate predicate);   

  long count(Predicate predicate);            

  boolean exists(Predicate predicate);        

  // … more functionality omitted.
}
 */
@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,//이걸 상속받게 해서 Article이 CRUD를 가능하게 해준다.
        QuerydslPredicateExecutor<Article>,//,Article에 대한 검색기능 추가
        QuerydslBinderCustomizer<QArticle>//Q클래스 사용
{
    @Override//QuerydslBinderCustomizer에서 QuerydslPredicateExecutor를통한 검색 설정
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);//검색범위를 모두로 할지 아니면 특정 값으로 할지 설정 default = false
        bindings.including(root.title,root.hashtag,root.createdAt,root.createdBy,root.content);//id를 통한 검색 제거
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);//title로 검색할 때 어떻게 비교할 지 확인 여기서는 검색시 완전일치가 아닌 포함으로 바꾸어줬다 기본은 완전일치
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);//title로 검색할 때 어떻게 비교할 지 확인 여기서는 검색시 완전일치가 아닌 포함으로 바꾸어줬다 기본은 완전일치
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);//StringExpression::containsIgnoreCase <-검색시 다르게 나옴
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}