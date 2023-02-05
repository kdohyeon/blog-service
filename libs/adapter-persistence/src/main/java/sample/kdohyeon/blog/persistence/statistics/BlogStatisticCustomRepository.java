package sample.kdohyeon.blog.persistence.statistics;

import sample.kdohyeon.blog.domain.statistics.BlogStatistic;

import java.util.List;
import java.util.Optional;

public interface BlogStatisticCustomRepository {
    Optional<BlogStatistic> findByKeyword(String keyword);

    List<BlogStatistic> findTopNByOrderByCountDesc(Long top);
}
