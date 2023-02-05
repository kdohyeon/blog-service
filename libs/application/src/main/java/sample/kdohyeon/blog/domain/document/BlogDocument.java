package sample.kdohyeon.blog.domain.document;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class BlogDocument {
    private final String title;
    private final String contents;
    private final String url;
    private final String blogName;
    private final String thumbnail;
    private final ZonedDateTime writtenAt;

    @Builder
    public BlogDocument(String title, String contents, String url,
                        String blogName, String thumbnail,
                        ZonedDateTime writtenAt) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogName = blogName;
        this.thumbnail = thumbnail;
        this.writtenAt = writtenAt;
    }
}
