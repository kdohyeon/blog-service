package sample.kdohyeon.blog.port.out.statistics;

import sample.kdohyeon.blog.domain.statistics.BlogStatistic;

import java.util.List;
import java.util.Optional;

public interface BlogStatisticsPort {
    BlogStatistic save(BlogStatistic statistic);

    Optional<BlogStatistic> findByKeyword(String keyword);

    List<BlogStatistic> findTopNByCount(Long top);
}
