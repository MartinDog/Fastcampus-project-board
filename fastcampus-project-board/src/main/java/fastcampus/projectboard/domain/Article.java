package fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter //Getter가 있어야 한다는 것을 정의
@ToString
@Table(indexes = )
public class Article {
    private Long id;
    private String title;
    private String hashtag;
    private String content;
    ///metadata
    private LocalDateTime createdAt;
    private String createdBy;
    private String creatdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
