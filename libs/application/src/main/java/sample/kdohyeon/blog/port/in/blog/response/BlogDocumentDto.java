package sample.kdohyeon.blog.port.in.blog.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final ZonedDateTime writtenAt;

    @Builder
    public BlogDocumentDto(String title, String contents, String url, String blogName, String thumbnail, ZonedDateTime writtenAt) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogName = blogName;
        this.thumbnail = thumbnail;
        this.writtenAt = writtenAt;
    }
}
