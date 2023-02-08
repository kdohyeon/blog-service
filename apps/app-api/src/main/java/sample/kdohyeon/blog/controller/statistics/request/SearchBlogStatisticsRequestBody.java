package sample.kdohyeon.blog.controller.statistics.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.beans.ConstructorProperties;
import java.util.Objects;

@Getter
public class SearchBlogStatisticsRequestBody {

    @Min(1)
    @Max(10)
    private Long top = 10L;

    @ConstructorProperties({"top"})
    public SearchBlogStatisticsRequestBody(Long top) {
        if (Objects.nonNull(top)) {
            this.top = top;
        }
    }
}
