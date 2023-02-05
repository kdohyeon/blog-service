package sample.kdohyeon.blog.service.blog;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.BuilderArbitraryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.kdohyeon.blog.converter.blog.BlogDtoConverter;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.domain.document.BlogDocument;
import sample.kdohyeon.blog.port.in.Pagination;
import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.statistics.UpsertBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.in.statistics.command.UpsertBlogStatisticsCommand;
import sample.kdohyeon.blog.port.out.blog.BlogPort;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceTest {
    @InjectMocks
    BlogSearchService sut;

    @Mock
    BlogPort blogPort;

    @Mock
    UpsertBlogStatisticsUseCase upsertBlogStatisticsUseCase;

    @Mock
    BlogDtoConverter blogDtoConverter;


    @BeforeEach
    void setup() {
        sut = new BlogSearchService(
                blogPort,
                upsertBlogStatisticsUseCase,
                blogDtoConverter
        );
    }

    @DisplayName("블로그를 조회할 땐, 검색어를 저장한다.")
    @Test
    void saveKeywordWhenSearchingBlogs() {
        // given
        var fixtureMonkey = FixtureMonkey.builder()
                .putGenerator(BlogSearchCommand.class, BuilderArbitraryGenerator.INSTANCE)
                .build();
        var command = fixtureMonkey.giveMeOne(BlogSearchCommand.class);

        // when
        sut.search(command);

        // then
        verify(upsertBlogStatisticsUseCase, times(1))
                .upsert(any(UpsertBlogStatisticsCommand.class));
    }

    @DisplayName("키워드로 블로그를 조회할 수 있다.")
    @Test
    void ableToSearchBlogsByKeyword() {
        // given
        var fixtureMonkey = FixtureMonkey.builder()
                .putGenerator(BlogSearchCommand.class, BuilderArbitraryGenerator.INSTANCE)
                .build();
        var command = fixtureMonkey.giveMeOne(BlogSearchCommand.class);

        // when
        sut.search(command);

        // then
        verify(blogPort, times(1))
                .searchBlogDocuments(any(BlogSearchClause.class));
    }

    @DisplayName("조회한 결과를 컨버터를 사용하여 변환한다.")
    @Test
    void convertResponseByConverter() {
        // given
        var fixtureMonkey = FixtureMonkey.builder()
                .putGenerator(BlogSearchCommand.class, BuilderArbitraryGenerator.INSTANCE)
                .putGenerator(Blog.class, BuilderArbitraryGenerator.INSTANCE)
                .putGenerator(Pagination.class, BuilderArbitraryGenerator.INSTANCE)
                .putGenerator(BlogDocument.class, BuilderArbitraryGenerator.INSTANCE)
                .build();
        var command = fixtureMonkey.giveMeOne(BlogSearchCommand.class);
        var blog = fixtureMonkey.giveMeOne(Blog.class);

        given(blogPort.searchBlogDocuments(any())).willReturn(blog);

        // when
        sut.search(command);

        // then
        verify(blogDtoConverter, times(1))
                .convert(any(Blog.class));
    }
}
