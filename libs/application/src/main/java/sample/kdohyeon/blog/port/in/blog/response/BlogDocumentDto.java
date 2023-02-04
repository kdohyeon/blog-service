package sample.kdohyeon.blog.port.in.blog.response;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class BlogDocumentDto {
    private final String title;
    private final String contents;
    private final String url;
    private final String blogName;
    private final String thumbnail;
    private final ZonedDateTime dateTime;

    @Builder
    public BlogDocumentDto(String title, String contents, String url, String blogName, String thumbnail, ZonedDateTime dateTime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogName = blogName;
        this.thumbnail = thumbnail;
        this.dateTime = dateTime;
    }
}
