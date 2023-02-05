package sample.kdohyeon.blog.converter.statistics;

import org.mapstruct.Mapper;
import sample.kdohyeon.blog.configure.MapStructConfig;
import sample.kdohyeon.blog.domain.statistics.BlogStatistic;
import sample.kdohyeon.blog.port.in.statistics.response.BlogStatisticsDto;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface BlogStatisticsDtoConverter {
    BlogStatisticsDto convert(BlogStatistic source);
    List<BlogStatisticsDto> convert(List<BlogStatistic> sources);
}
