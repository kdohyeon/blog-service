package sample.kdohyeon.blog.http.blog;

import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.http.blog.converter.BlogConverter;
import sample.kdohyeon.blog.http.blog.response.KakaoBlogResponse;
import sample.kdohyeon.blog.http.util.ObjectMapperUtil;
import sample.kdohyeon.blog.port.output.blog.clause.KakaoBlogSearchClause;

@Component
public class KakaoBlogHttpClient {

    private final BlogRestTemplateClient blogRestTemplateClient;
    private final BlogConverter blogConverter;
    private final ObjectMapperUtil objectMapperUtil;

    public KakaoBlogHttpClient(BlogRestTemplateClient blogRestTemplateClient,
                               BlogConverter blogConverter,
                               ObjectMapperUtil objectMapperUtil) {
        this.blogRestTemplateClient = blogRestTemplateClient;
        this.blogConverter = blogConverter;
        this.objectMapperUtil = objectMapperUtil;
    }

    public Blog searchBlogDocuments(KakaoBlogSearchClause clause) {
        var response = blogRestTemplateClient.query(clause);
        KakaoBlogResponse kakaoBlogResponse = objectMapperUtil.readValue(response, KakaoBlogResponse.class);
        return blogConverter.converter(kakaoBlogResponse, clause);
    }
}
