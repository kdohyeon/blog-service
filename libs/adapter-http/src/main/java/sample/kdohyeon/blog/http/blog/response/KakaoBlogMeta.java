package sample.kdohyeon.blog.http.blog.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class KakaoBlogMeta {
    @JsonProperty("total_count")
    private final Long totalCount;

    @JsonProperty("pageable_count")
    private final int pageableCount;

    @JsonProperty("is_end")
    private final boolean isEnd;

    @ConstructorProperties({"totalCount", "pageableCount", "isEnd"})
    public KakaoBlogMeta(Long totalCount, int pageableCount, boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
