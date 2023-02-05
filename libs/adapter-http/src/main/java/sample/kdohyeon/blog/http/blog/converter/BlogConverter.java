package sample.kdohyeon.blog.http.blog.converter;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.domain.document.BlogDocument;
import sample.kdohyeon.blog.http.blog.response.KakaoBlogResponse;
import sample.kdohyeon.blog.port.in.Pagination;

@Component
public class BlogConverter {
    public Blog converter(KakaoBlogResponse source, Pageable pageable) {
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
                                pageable.getPageNumber(),
                                meta.getPageableCount(),
                                meta.getTotalCount(),
                                pageable.getPageSize()
                        )
                )
                .build();
    }
}
