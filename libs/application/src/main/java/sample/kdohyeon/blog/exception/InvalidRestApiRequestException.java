package sample.kdohyeon.blog.exception;

import sample.kdohyeon.blog.contract.RestApiType;

public class InvalidRestApiRequestException extends RuntimeException {
    public InvalidRestApiRequestException(RestApiType type) {
        super(String.format("%s API 는 요청할 수 없습니다.", type));
    }
}
