package fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
@Getter //Getter가 있어야 한다는 것을 정의
//@EqualsAndHashCode collection에서 리스트에서 중복 제거, 동일성 확인 때 equal함수를 JPA에서 정의해 준다. 이걸 사용하면 전체 내용을 비교하는 것이기 때문에 이번에는 사용하지 않았다
@ToString
@Table(indexes = { //index로 설정하는 모습이다.
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")

}
)
@Entity
public class Article extends AuditingFields{ // h2 hiderate기준으로 엔터티로 설정되면 생성자를 필요로 한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// 유저 정보 (ID)
    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; // 해시태그

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    //현재 tostring이 출력을 위해서 Articlecomment에서 조회중에 Article 항목으로 들어가고 그 항목에서 이 Set<ArticleComment>로 가고 거기서 또
    //Article을 참조하면서 순환 참조가 일어나기 때문에 @ToString에서 제외할 부분으로 이 것을 설정해준 것이다.

    ///metadata
    //자동으로 값들을 추가해준다 JPA의 기능
    protected Article(){

    }
    private Article(String title,String content,String hashtag){
        this.title=title;
        this.content=content;
        this.hashtag=hashtag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id!=null && id.equals(article.id);//새로 생성된 엔터티들이 영속화가 이루어져 있지 않으면 그냥 false로 한다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Article of(String title, String content, String hashtag){
        return new Article(title,content,hashtag);
    }

}
