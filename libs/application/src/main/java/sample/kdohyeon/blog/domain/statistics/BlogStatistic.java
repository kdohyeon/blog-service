package sample.kdohyeon.blog.domain.statistics;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "blog_statistics")
public class BlogStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_statistic_id")
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

    public void count() {
        this.count++;
    }

    public static BlogStatistic create(CreateBlogStatistic create) {
        return BlogStatistic.builder()
                .keyword(create.getKeyword())
                .count(1L)
                .build();
    }
}