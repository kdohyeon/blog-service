package sample.kdohyeon.blog.port.in.statistics.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpsertBlogStatisticsCommand {
    private final String keyword;

    @Builder
    public UpsertBlogStatisticsCommand(String keyword) {
        this.keyword = keyword;
    }
}
