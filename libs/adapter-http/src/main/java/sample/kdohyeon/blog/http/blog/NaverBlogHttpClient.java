package sample.kdohyeon.blog.http.blog;

import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.http.blog.converter.BlogConverter;
import sample.kdohyeon.blog.http.blog.response.NaverBlogResponse;
import sample.kdohyeon.blog.http.util.ObjectMapperUtil;
import sample.kdohyeon.blog.port.output.blog.clause.NaverBlogSearchClause;

@Component
public class NaverBlogHttpClient {

    private final BlogRestTemplateClient blogRestTemplateClient;
    private final BlogConverter blogConverter;
    private final ObjectMapperUtil objectMapperUtil;

    public NaverBlogHttpClient(BlogRestTemplateClient blogRestTemplateClient,
                               BlogConverter blogConverter,
                               ObjectMapperUtil objectMapperUtil) {
        this.blogRestTemplateClient = blogRestTemplateClient;
        this.blogConverter = blogConverter;
        this.objectMapperUtil = objectMapperUtil;
    }

    public Blog searchBlogDocuments(NaverBlogSearchClause clause) {
        var response = blogRestTemplateClient.query(clause);
        NaverBlogResponse blogResponse = objectMapperUtil.readValue(response, NaverBlogResponse.class);
        return blogConverter.converter(blogResponse, clause);
    }
}
