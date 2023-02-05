package sample.kdohyeon.blog.persistence.statistics;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import sample.kdohyeon.blog.domain.statistics.BlogStatistic;
import sample.kdohyeon.blog.port.out.statistics.BlogStatisticsPort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static sample.kdohyeon.blog.domain.statistics.QBlogStatistic.blogStatistic;

@Repository
public class BlogStatisticsRepository extends QuerydslRepositorySupport implements BlogStatisticsPort {
    public BlogStatisticsRepository() {
        super(BlogStatistic.class);
    }

    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    @Override
    public BlogStatistic save(BlogStatistic statistic) {
        EntityManager entityManager = getEntityManager();
        Assert.notNull(entityManager, "Entity manager must not null.");
        entityManager.persist(statistic);
        entityManager.flush();
        return statistic;
    }

    @Override
    public Optional<BlogStatistic> findByKeyword(String keyword) {
        BooleanBuilder condition = new BooleanBuilder();

        if (StringUtils.isNotBlank(keyword)) {
            condition.and(blogStatistic.keyword.eq(keyword));
        }

        return from(blogStatistic)
                .where(condition)
                .stream()
                .findAny();
    }

    @Override
    public List<BlogStatistic> findTopNByCount(Long top) {
        return from(blogStatistic)
                .orderBy(blogStatistic.count.desc())
                .limit(top)
                .fetch();
    }
}
