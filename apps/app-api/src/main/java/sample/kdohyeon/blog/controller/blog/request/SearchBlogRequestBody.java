package sample.kdohyeon.blog.controller.blog.request;

import lombok.Getter;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;

@Getter
public class SearchBlogRequestBody {
    @NotBlank(message = "키워드를 입력하세요.")
    private final String keyword;
    private final String url;
    private final BlogSearchQuerySort sort;

    @ConstructorProperties({"keyword", "url", "sort"})
    public SearchBlogRequestBody(String keyword, String url, BlogSearchQuerySort sort) {
        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
    }
}
