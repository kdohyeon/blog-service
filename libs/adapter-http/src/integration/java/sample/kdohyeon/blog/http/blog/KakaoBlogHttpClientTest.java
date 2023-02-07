package sample.kdohyeon.blog.http.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.http.HttpIntegrationTest;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public class KakaoBlogHttpClientTest extends HttpIntegrationTest {
    @Autowired
    private KakaoBlogHttpClient sut;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void testKakaoBlogHttpClient() {
        var clause = BlogSearchClause.builder()
                .page(1)
                .size(10)
                .sort(BlogSearchQuerySort.ACCURACY)
                .keyword("keyword")
                .build();

        var expectedUrl = String.format(
                "https://dapi.kakao.com/v2/search/blog?query=%s&sort=accuracy&page=%s&size=%s",
                clause.getKeyword(),
                clause.getPage(),
                clause.getSize()
        );

        var httpMethod = HttpMethod.GET;

        mockServer.expect(requestTo(expectedUrl))
                .andExpect(method(httpMethod))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(new ClassPathResource("http/blog/kakao_blog_sample_data.json"))
                );

        var actual = sut.searchBlogDocuments(clause);

        assertThat(actual.getBlogDocuments()).hasSize(10);
        assertThat(actual.getBlogDocuments()).element(0)
                .hasFieldOrPropertyWithValue("title", "\u003cb\u003e스프링\u003c/b\u003e부트 웹 서비스 AWS에 자동 배포하기")
                .hasFieldOrPropertyWithValue("contents", "Github에 있습니다.) 6-1. CI? 이전 시간에 저희는 \u003cb\u003e스프링\u003c/b\u003e부트 프로젝트를 간단하게나마 EC2에 배포해보았습니다. 스크립트를 개발자 \u003cb\u003ejojoldu.tistory.com\u003c/b\u003e 4. 무중단배포 7) \u003cb\u003e스프링\u003c/b\u003e부트로 웹 서비스 출시하기 - 7. Nginx를 활용한 무중단 배포 구축하기 이번 시간엔 무중단 배포 환경을 구축하겠습니다. (모든 코드는...")
                .hasFieldOrPropertyWithValue("url", "http://tosim.tistory.com/133")
                .hasFieldOrPropertyWithValue("blogName", "습작")
                .hasFieldOrPropertyWithValue("thumbnail", "")
        ;
    }
}
