package sample.kdohyeon.blog.controller.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.kdohyeon.blog.port.in.statistics.SearchBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.in.statistics.command.SearchBlogStatisticsCommand;
import sample.kdohyeon.blog.port.in.statistics.response.BlogStatisticsDto;
import sample.kdohyeon.common.protocol.response.ResultResponse;

import java.util.List;

@RestController
public class BlogStatisticsSearchController {

    private final SearchBlogStatisticsUseCase searchBlogStatisticsUseCase;

    public BlogStatisticsSearchController(SearchBlogStatisticsUseCase searchBlogStatisticsUseCase) {
        this.searchBlogStatisticsUseCase = searchBlogStatisticsUseCase;
    }

    @GetMapping("/api/v1/blogs/statistics/popular")
    public ResultResponse<List<BlogStatisticsDto>> searchPopularSearchKeyword(
            @RequestParam(defaultValue = "10", required = false) Long top
    ) {
        var command = SearchBlogStatisticsCommand.builder()
                .top(top)
                .build();
        return ResultResponse.ok(searchBlogStatisticsUseCase.search(command));
    }
}
