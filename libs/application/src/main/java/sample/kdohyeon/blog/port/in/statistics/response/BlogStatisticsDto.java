package sample.kdohyeon.blog.port.in.statistics.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogStatisticsDto {
    private final String keyword;
    private final Long count;

    @Builder
    public BlogStatisticsDto(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
