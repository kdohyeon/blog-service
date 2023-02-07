package sample.kdohyeon.blog.port.input.statistics;

import sample.kdohyeon.blog.port.input.statistics.command.SearchBlogStatisticsCommand;
import sample.kdohyeon.blog.port.input.statistics.response.BlogStatisticsDto;

import java.util.List;

public interface SearchBlogStatisticsUseCase {
    List<BlogStatisticsDto> search(SearchBlogStatisticsCommand command);
}
