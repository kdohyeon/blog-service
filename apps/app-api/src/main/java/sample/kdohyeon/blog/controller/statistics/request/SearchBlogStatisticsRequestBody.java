package sample.kdohyeon.blog.controller.statistics.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

@Getter
public class SearchBlogStatisticsRequestBody {
    @NotNull
    @Min(1)
    @Max(10)
    private final Long top;

    @ConstructorProperties({"top"})
    public SearchBlogStatisticsRequestBody(Long top) {
        this.top = top;
    }
}
