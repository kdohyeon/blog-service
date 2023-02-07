package sample.kdohyeon.blog.port.input.statistics.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchBlogStatisticsCommand {
    private final Long top;

    @Builder
    public SearchBlogStatisticsCommand(Long top) {
        this.top = top;
    }
}
