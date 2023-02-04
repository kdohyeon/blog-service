package sample.kdohyeon.blog.http.exception;

public class InvalidServiceProviderException extends RuntimeException {
    public InvalidServiceProviderException(String serviceProvider) {
        super(String.format("해당 서비스 프로바이더를 찾을 수 없습니다. %s", serviceProvider));
    }
}
