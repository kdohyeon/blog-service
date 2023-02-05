package sample.kdohyeon.blog.port.in.blog.response;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import sample.kdohyeon.blog.port.in.Pagination;

import java.util.List;

@Getter
public class BlogDto {
    private final List<BlogDocumentDto> documents;
    private final Pagination pagination;

    @Builder
    public BlogDto(List<BlogDocumentDto> documents, Pagination pagination) {
        this.documents = documents;
        this.pagination = pagination;
    }

    public static BlogDto empty() {
        return BlogDto.builder()
                .documents(Lists.newArrayList())
                .pagination(Pagination.empty())
                .build();
    }
}
