package sample.kdohyeon.blog.converter.blog;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.BuilderArbitraryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.domain.document.BlogDocument;
import sample.kdohyeon.blog.port.in.Pagination;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BlogDtoConverterTest {
    @InjectMocks
    private BlogDtoConverter sut;

    @BeforeEach
    void setup() {
        sut = new BlogDtoConverter(
                new BlogDocumentDtoConverterImpl()
        );
    }

    @Test
    @DisplayName("Blog 를 BlogDto 로 변환할 수 있다.")
    void convert() {
        var fixtureMonkey = FixtureMonkey.builder()
                .putGenerator(Blog.class, BuilderArbitraryGenerator.INSTANCE)
                .putGenerator(BlogDocument.class, BuilderArbitraryGenerator.INSTANCE)
                .putGenerator(Pagination.class, BuilderArbitraryGenerator.INSTANCE)
                .build();

        var blogDocuments = fixtureMonkey.giveMe(BlogDocument.class, 5);
        var pagination = fixtureMonkey.giveMeOne(Pagination.class);

        var blog = Blog.builder()
                .blogDocuments(blogDocuments)
                .pagination(pagination)
                .build();


        var firstBlog = blog.getBlogDocuments().get(0);

        var blogDto = sut.convert(blog);

        assertThat(blogDto).isNotNull();
        assertThat(blogDto.getDocuments()).hasSize(blog.getBlogDocuments().size());
        assertThat(blogDto.getDocuments()).element(0)
                .hasFieldOrPropertyWithValue("title", firstBlog.getTitle())
                .hasFieldOrPropertyWithValue("contents", firstBlog.getContents())
                .hasFieldOrPropertyWithValue("url", firstBlog.getUrl())
                .hasFieldOrPropertyWithValue("blogName", firstBlog.getBlogName())
                .hasFieldOrPropertyWithValue("thumbnail", firstBlog.getThumbnail())
                .hasFieldOrPropertyWithValue("writtenAt", firstBlog.getWrittenAt())
        ;
    }
}
