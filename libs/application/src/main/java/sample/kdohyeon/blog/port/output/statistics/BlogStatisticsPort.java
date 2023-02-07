package sample.kdohyeon.blog.port.output.statistics;

import sample.kdohyeon.blog.domain.statistics.BlogStatistic;

import java.util.List;
import java.util.Optional;

public interface BlogStatisticsPort {
    void save(BlogStatistic blogStatistic);

    Optional<BlogStatistic> findByKeyword(String keyword);

    List<BlogStatistic> findTopNByOrderByCountDesc(Long top);
}
