package sample.kdohyeon.blog.persistence.statistics;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import sample.kdohyeon.blog.domain.statistics.BlogStatistic;

import java.util.List;
import java.util.Optional;

import static sample.kdohyeon.blog.domain.statistics.QBlogStatistic.blogStatistic;

@Repository
public class BlogStatisticCustomRepositoryImpl implements BlogStatisticCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public BlogStatisticCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<BlogStatistic> findByKeyword(String keyword) {
        BooleanBuilder condition = new BooleanBuilder();

        if (StringUtils.isNotBlank(keyword)) {
            condition.and(blogStatistic.keyword.eq(keyword));
        }

        return jpaQueryFactory.selectFrom(blogStatistic)
                .where(condition)
                .stream()
                .findAny();
    }

    @Override
    public List<BlogStatistic> findTopNByOrderByCountDesc(Long top) {
        return jpaQueryFactory.selectFrom(blogStatistic)
                .orderBy(blogStatistic.count.desc())
                .limit(top)
                .fetch();
    }
}
