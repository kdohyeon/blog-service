package sample.kdohyeon.blog.http.blog.response;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class NaverBlogItem {
    private final String title;
    private final String link;
    private final String description;
    private final String bloggername;
    private final String bloggerlink;
    private final String postdate;

    @ConstructorProperties({
            "title", "link", "description",
            "bloggername", "bloggerlink", "postdate"
    })
    public NaverBlogItem(String title, String link, String description,
                         String bloggername, String bloggerlink,
                         String postdate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.bloggername = bloggername;
        this.bloggerlink = bloggerlink;
        this.postdate = postdate;
    }
}
