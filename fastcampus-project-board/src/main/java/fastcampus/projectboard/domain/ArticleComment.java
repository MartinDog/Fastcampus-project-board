package fastcampus.projectboard.domain;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
//protected ArticleComment() {}를 대신 만들어 주는 것이다.

@Getter //Getter가 있어야 한다는 것을 정의
//@EqualsAndHashCode collection에서 리스트에서 중복 제거, 동일성 확인 때 equal함수를 JPA에서 정의해 준다. 이걸 사용하면 전체 내용을 비교하는 것이기 때문에 이번에는 사용하지 않았다
@ToString
@Table(indexes = { //index로 설정하는 모습이다.
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")

}
)
@EntityListeners(AuditingEntityListener.class)//<-Auditing을 사용한다는 표시이다.
@Entity
public class ArticleComment {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id!=null&& id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Setter @Column(nullable = false, length = 500) private String content;
    @Setter @ManyToOne(optional=false) private Article article;

    @CreatedDate @Column (nullable=false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable=false) private String createdBy;
    @LastModifiedDate @Column(nullable=false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable=false) private String modifiedBy;
    protected ArticleComment(){}
    public ArticleComment(String content, Article article) {
        this.content = content;
        this.article = article;
    }
    public static ArticleComment of(String content, Article article) {
        return new ArticleComment(content,article);
    }

}
