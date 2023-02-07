package sample.kdohyeon.blog.converter.blog;

import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.port.input.blog.response.BlogDto;

import java.util.Objects;

@Component
public class BlogDtoConverter {

    private final BlogDocumentDtoConverter blogDocumentDtoConverter;

    public BlogDtoConverter(BlogDocumentDtoConverter blogDocumentDtoConverter) {
        this.blogDocumentDtoConverter = blogDocumentDtoConverter;
    }


    public BlogDto convert(Blog source) {
        if (Objects.isNull(source)) {
            return BlogDto.empty();
        }

        var documents = source.getBlogDocuments();
        var pagination = source.getPagination();
        return BlogDto.builder()
                .documents(blogDocumentDtoConverter.convert(documents))
                .pagination(pagination)
                .build();
    }
}
