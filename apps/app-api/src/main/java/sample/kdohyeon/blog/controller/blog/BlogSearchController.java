package sample.kdohyeon.blog.controller.blog;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.kdohyeon.blog.controller.blog.request.SearchBlogRequestBody;
import sample.kdohyeon.blog.port.in.blog.SearchBlogUseCase;
import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.blog.response.BlogDto;
import sample.kdohyeon.common.protocol.response.ResultResponse;

import javax.validation.Valid;

@RestController
public class BlogSearchController {

    private final SearchBlogUseCase searchBlogUseCase;

    public BlogSearchController(SearchBlogUseCase searchBlogUseCase) {
        this.searchBlogUseCase = searchBlogUseCase;
    }

    @GetMapping("/api/v1/blogs")
    public ResultResponse<BlogDto> searchBlogs(
            @Valid SearchBlogRequestBody requestBody,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var command = BlogSearchCommand.builder()
                .keyword(requestBody.getKeyword())
                .url(requestBody.getUrl())
                .sort(requestBody.getSort())
                .build();

        var pageable = PageRequest.of(page, size);

        return ResultResponse.ok(searchBlogUseCase.search(command, pageable));
    }
}
