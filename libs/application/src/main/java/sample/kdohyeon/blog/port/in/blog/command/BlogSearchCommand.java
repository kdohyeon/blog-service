package sample.kdohyeon.blog.port.in.blog.command;

import lombok.Builder;
import lombok.Getter;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;

@Getter
public class BlogSearchCommand {
    private final String keyword;
    private final String url;
    private final BlogSearchQuerySort sort;

    @Builder
    public BlogSearchCommand(String keyword, String url, BlogSearchQuerySort sort) {
        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
    }
}
