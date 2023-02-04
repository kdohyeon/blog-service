package sample.kdohyeon.blog.http.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;

@Component
public class GsonUtil {
    private Gson gson;

    @PostConstruct
    public void afterPropertiesSet() {
        gson = new GsonBuilder().serializeNulls().create();
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T fromJson(String json, Type type) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }
}
