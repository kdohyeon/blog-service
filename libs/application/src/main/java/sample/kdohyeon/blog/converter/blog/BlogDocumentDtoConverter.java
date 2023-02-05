package sample.kdohyeon.blog.converter.blog;

import org.mapstruct.Mapper;
import sample.kdohyeon.blog.configure.MapStructConfig;
import sample.kdohyeon.blog.domain.document.BlogDocument;
import sample.kdohyeon.blog.port.in.blog.response.BlogDocumentDto;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface BlogDocumentDtoConverter {
    BlogDocumentDto convert(BlogDocument source);
    List<BlogDocumentDto> convert(List<BlogDocument> sources);
}
