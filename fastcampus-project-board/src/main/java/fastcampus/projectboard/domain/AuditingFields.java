package fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)//jpa 어디팅을 사용한다는 것을 알린다.
@MappedSuperclass//해당 클래스가 다른 엔터티 클래스에서 삽입되어 사용되는 것을 알림.
public class AuditingFields {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable=false,updatable = false)
    private LocalDateTime createdAt;// 생성일자

    @CreatedBy
    @Column(nullable=false,updatable = false)
    private String createdBy;//생성자

    @LastModifiedDate
    @Column(nullable=false)
    private LocalDateTime modifiedAt;//수정일자

    @LastModifiedBy
    @Column(nullable=false)
    private String modifiedBy;//수정자
}
