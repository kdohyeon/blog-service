package sample.kdohyeon.blog.http.blog.response;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
public class KakaoBlogResponse {
    private final KakaoBlogMeta meta;
    private final List<KakaoBlogDocument> documents;

    @ConstructorProperties({"meta", "documents"})
    public KakaoBlogResponse(KakaoBlogMeta meta, List<KakaoBlogDocument> documents) {
        this.meta = meta;
        this.documents = documents;
    }
}
