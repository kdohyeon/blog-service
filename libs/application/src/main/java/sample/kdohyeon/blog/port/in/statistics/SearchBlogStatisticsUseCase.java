package sample.kdohyeon.blog.port.in.statistics;

import sample.kdohyeon.blog.port.in.statistics.command.SearchBlogStatisticsCommand;
import sample.kdohyeon.blog.port.in.statistics.response.BlogStatisticsDto;

import java.util.List;

public interface SearchBlogStatisticsUseCase {
    List<BlogStatisticsDto> search(SearchBlogStatisticsCommand command);
}
