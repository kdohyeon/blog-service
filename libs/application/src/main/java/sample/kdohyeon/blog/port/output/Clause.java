package sample.kdohyeon.blog.port.output;

import lombok.Getter;
import sample.kdohyeon.blog.contract.RestApiType;

@Getter
public class Clause {
    private final RestApiType restApiType;

    public Clause(RestApiType restApiType) {
        this.restApiType = restApiType;
    }
}
