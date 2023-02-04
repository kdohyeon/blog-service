package sample.kdohyeon.blog.http.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class StringDeserializer {

	private final GsonUtil gsonUtil;

	@Autowired
	public StringDeserializer(GsonUtil gsonUtil) {
		this.gsonUtil = gsonUtil;
	}

	public <T> List<T> deserializeAsList(String response, Class<T> clazz) {
		if (StringUtils.isBlank(response)) {
			return Lists.newArrayList();
		}

		Type type = new ListParameterizedType(clazz);
		return gsonUtil.fromJson(response, type);
	}

	public <T> T deserializeAsObject(String response, Class<T> clazz) {
		if (StringUtils.isBlank(response)) {
			return null;
		}

		return gsonUtil.fromJson(response, clazz);
	}
}
