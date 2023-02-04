package sample.kdohyeon.blog.http.blog.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class KakaoBlogMeta {
    @SerializedName("total_count")
    private final Long totalCount;

    @SerializedName("pageable_count")
    private final int pageableCount;

    @SerializedName("is_end")
    private final boolean isEnd;

    @ConstructorProperties({"totalCount", "pageableCount", "isEnd"})
    public KakaoBlogMeta(Long totalCount, int pageableCount, boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
