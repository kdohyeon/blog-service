package sample.kdohyeon.blog.controller.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.kdohyeon.blog.controller.blog.request.SearchBlogRequestBody;
import sample.kdohyeon.blog.port.input.blog.SearchBlogUseCase;
import sample.kdohyeon.blog.port.input.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.input.blog.response.BlogDto;
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
                .page(page) // db 조회였다면 pageable 를 사용했을텐데 외부 API 호출이라 굳이 사용하지 않음
                .size(size)
                .build();

        return ResultResponse.ok(searchBlogUseCase.search(command));
    }
}
