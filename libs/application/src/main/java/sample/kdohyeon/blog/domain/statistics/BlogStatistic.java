package sample.kdohyeon.blog.domain.statistics;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sample.kdohyeon.blog.domain.MutableBaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BlogStatistic extends MutableBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Long count;

    @Builder
    public BlogStatistic(Long id, String keyword, Long count) {
        this.id = id;
        this.keyword = keyword;
        this.count = count;
    }

    public void increaseCount() {
        this.count++;
    }

    public static BlogStatistic create(CreateBlogStatistic create) {
        return BlogStatistic.builder()
                .keyword(create.getKeyword())
                .count(1L)
                .build();
    }
}
