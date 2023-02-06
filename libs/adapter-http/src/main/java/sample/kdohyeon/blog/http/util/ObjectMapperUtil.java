package sample.kdohyeon.blog.http.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.exception.SerializationException;

@Component
public class ObjectMapperUtil {

    private final ObjectMapper objectMapper;

    public ObjectMapperUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T readValue(String s, Class<T> tClass) {
        try {
            return objectMapper.readValue(s, tClass);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
