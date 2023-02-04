package sample.kdohyeon.blog.contract;

import lombok.Getter;
import org.springframework.http.HttpMethod;

public enum RestApiType {
    // KAKAO
    KAKAO_SEARCH_BLOGS("/v2/search/blog", HttpMethod.GET, ServiceProvider.KAKAO),

    // NAVER
    NAVER_SEARCH_BLOGS("/v1/search/blog.json", HttpMethod.GET, ServiceProvider.NAVER),
    ;

    @Getter
    private final String uri;

    @Getter
    private final HttpMethod httpMethod;

    @Getter
    private final ServiceProvider serviceProvider;

    RestApiType(String uri, HttpMethod httpMethod, ServiceProvider serviceProvider) {
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.serviceProvider = serviceProvider;
    }
}
