package sample.kdohyeon.blog.http.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sample.kdohyeon.blog.domain.blog.Blog;
import sample.kdohyeon.blog.http.blog.converter.BlogConverter;
import sample.kdohyeon.blog.http.blog.response.KakaoBlogResponse;
import sample.kdohyeon.blog.http.builder.UriBuilder;
import sample.kdohyeon.blog.http.contract.ServiceProvider;
import sample.kdohyeon.blog.http.exception.InvalidServiceProviderException;
import sample.kdohyeon.blog.port.out.blog.SearchBlogPort;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

import java.util.List;

@Component
public class KakaoSearchBlogHttpClient implements SearchBlogPort {

    private static final ServiceProvider PROVIDER = ServiceProvider.KAKAO;

    private final RestTemplate restTemplate;
    private final List<UriBuilder> uriBuilders;
    private final String restApiKey;
    private final BlogConverter blogConverter;

    public KakaoSearchBlogHttpClient(
            RestTemplate restTemplate,
            List<UriBuilder> uriBuilders,
            @Value("${kakao-open-api.auth.rest-api-key}") String restApiKey,
            BlogConverter blogConverter
    ) {
        this.restTemplate = restTemplate;
        this.uriBuilders = uriBuilders;
        this.restApiKey = restApiKey;
        this.blogConverter = blogConverter;
    }

    @Override
    public Blog searchBlogs(BlogSearchClause clause, Pageable pageable) {
        var uri = uriBuilders.stream()
                .filter(each -> each.isTarget(PROVIDER))
                .findAny()
                .orElseThrow(() -> new InvalidServiceProviderException(PROVIDER.name()))
                .buildBlogSearchUri(clause, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + restApiKey);

        var kakaoBlogResponse = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<KakaoBlogResponse>() {
                }
        ).getBody();

        return blogConverter.converter(kakaoBlogResponse, pageable);
    }
}
