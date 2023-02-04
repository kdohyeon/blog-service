package sample.kdohyeon.blog.port.in.blog.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogMetaDto {
    private final Long totalCount;
    private final Long pageableCount;
    private final boolean isEnd;

    @Builder
    public BlogMetaDto(Long totalCount, Long pageableCount, boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
