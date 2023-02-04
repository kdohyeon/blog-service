package sample.kdohyeon.blog.port.in.blog.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BlogDto {
    private final BlogMetaDto meta;
    private final List<BlogDocumentDto> documents;

    @Builder
    public BlogDto(BlogMetaDto meta, List<BlogDocumentDto> documents) {
        this.meta = meta;
        this.documents = documents;
    }
}
