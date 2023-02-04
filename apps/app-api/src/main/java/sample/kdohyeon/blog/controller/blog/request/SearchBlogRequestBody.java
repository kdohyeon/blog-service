package sample.kdohyeon.blog.controller.blog.request;

import lombok.Getter;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;

import java.beans.ConstructorProperties;

@Getter
public class SearchBlogRequestBody {
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
