package sample.kdohyeon.blog.http.blog.response;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.ZonedDateTime;

@Getter
public class KakaoBlogDocument {
    private final String title;
    private final String contents;
    private final String url;
    private final String blogname;
    private final String thumbnail;
    private final ZonedDateTime datetime;

    @ConstructorProperties({"title", "contents", "url", "blogname", "thumbnail", "datetime"})
    public KakaoBlogDocument(String title, String contents, String url, String blogname, String thumbnail, ZonedDateTime datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.thumbnail = thumbnail;
        this.datetime = datetime;
    }
}
