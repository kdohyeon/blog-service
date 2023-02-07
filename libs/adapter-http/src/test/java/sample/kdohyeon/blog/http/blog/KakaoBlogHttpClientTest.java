package sample.kdohyeon.blog.http.blog;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.http.blog.converter.BlogConverter;
import sample.kdohyeon.blog.http.builder.KakaoBlogSearchBuilder;
import sample.kdohyeon.blog.http.client.HttpClient;
import sample.kdohyeon.blog.http.util.ObjectMapperUtil;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class KakaoBlogHttpClientTest {
    @InjectMocks
    private KakaoBlogHttpClient sut;

    @Mock
    private HttpClient httpClient;

    @Mock
    private KakaoBlogSearchBuilder kakaoBlogSearchBuilder;

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @BeforeEach
    void setup() {
        sut = new KakaoBlogHttpClient(
                Lists.newArrayList(kakaoBlogSearchBuilder),
                new BlogConverter(),
                httpClient,
                objectMapperUtil
        );
    }

    @Test
    void searchBlogDocuments() {
        // given
        BlogSearchClause clause = BlogSearchClause.builder()
                .build();
        given(httpClient.request(any(), any(), any())).willReturn("aa");
        given(kakaoBlogSearchBuilder.isTarget(RestApiType.KAKAO_SEARCH_BLOGS)).willReturn(true);

        // when
//        Blog result = sut.searchBlogDocuments(clause);

        // then

    }
}
