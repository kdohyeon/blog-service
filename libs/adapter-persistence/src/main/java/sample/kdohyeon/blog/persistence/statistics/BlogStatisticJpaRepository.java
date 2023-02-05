package sample.kdohyeon.blog.persistence.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.kdohyeon.blog.domain.statistics.BlogStatistic;

public interface BlogStatisticJpaRepository extends JpaRepository<BlogStatistic, Long>, BlogStatisticCustomRepository {

}
