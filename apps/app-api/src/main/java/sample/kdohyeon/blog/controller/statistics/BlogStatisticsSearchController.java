package sample.kdohyeon.blog.controller.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.kdohyeon.blog.controller.statistics.request.SearchBlogStatisticsRequestBody;
import sample.kdohyeon.blog.port.input.statistics.SearchBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.input.statistics.command.SearchBlogStatisticsCommand;
import sample.kdohyeon.blog.port.input.statistics.response.BlogStatisticsDto;
import sample.kdohyeon.common.protocol.response.ResultResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BlogStatisticsSearchController {

    private final SearchBlogStatisticsUseCase searchBlogStatisticsUseCase;

    public BlogStatisticsSearchController(SearchBlogStatisticsUseCase searchBlogStatisticsUseCase) {
        this.searchBlogStatisticsUseCase = searchBlogStatisticsUseCase;
    }

    @GetMapping("/api/v1/blogs/statistics/popular")
    public ResultResponse<List<BlogStatisticsDto>> searchPopularSearchKeyword(
            @Valid SearchBlogStatisticsRequestBody requestBody
    ) {
        var command = SearchBlogStatisticsCommand.builder()
                .top(requestBody.getTop())
                .build();
        return ResultResponse.ok(searchBlogStatisticsUseCase.search(command));
    }
}
