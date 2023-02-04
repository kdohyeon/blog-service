package sample.kdohyeon.blog.contract.blog;

import lombok.Getter;

public enum BlogSearchQuerySort {
    ACCURACY("정확도순")
    , RECENCY("최신순")
    ;

    @Getter
    private final String name;

    BlogSearchQuerySort(String name) {
        this.name = name;
    }
}
