package sample.kdohyeon.blog.http.blog.converter;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.domain.document.BlogDocument;
import sample.kdohyeon.blog.http.blog.response.KakaoBlogResponse;
import sample.kdohyeon.blog.http.blog.response.NaverBlogResponse;
import sample.kdohyeon.blog.port.input.Pagination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BlogConverter {
    public Blog converter(KakaoBlogResponse source, int page, int size) {
        var documents = source.getDocuments();
        var meta = source.getMeta();

        return Blog.builder()
                .blogDocuments(
                        documents.stream()
                                .map(each -> BlogDocument.builder()
                                        .title(each.getTitle())
                                        .contents(each.getContents())
                                        .blogName(each.getBlogname())
                                        .url(each.getUrl())
                                        .thumbnail(each.getThumbnail())
                                        .writtenAt(each.getDatetime())
                                        .build())
                                .toList()
                )
                .pagination(
                        new Pagination(
                                page,
                                meta.getPageableCount(),
                                meta.getTotalCount(),
                                size
                        )
                )
                .build();
    }

    public Blog converter(NaverBlogResponse source, int page, int size) {
        var documents = source.getItems();

        return Blog.builder()
                .blogDocuments(
                        documents.stream()
                                .map(each -> BlogDocument.builder()
                                        .title(each.getTitle())
                                        .contents(each.getDescription())
                                        .blogName(each.getBloggername())
                                        .url(each.getLink())
                                        .thumbnail(Strings.EMPTY)
                                        .writtenAt(
                                                ZonedDateTime.of(
                                                        LocalDate.parse(each.getPostdate(), DateTimeFormatter.BASIC_ISO_DATE),
                                                        LocalTime.MIDNIGHT,
                                                        ZoneId.systemDefault())
                                        )
                                        .build())
                                .toList()
                )
                .pagination(
                        new Pagination(
                                page,
                                (int) (source.getTotal() / size) + 1,
                                source.getTotal(),
                                size
                        )
                )
                .build();
    }
}
