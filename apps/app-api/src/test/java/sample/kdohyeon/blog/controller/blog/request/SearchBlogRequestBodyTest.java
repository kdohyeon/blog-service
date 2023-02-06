package sample.kdohyeon.blog.controller.blog.request;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SearchBlogRequestBodyTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void close() {
        factory.close();
    }

    @DisplayName("블로그 검색을 하기 위해서는 keyword 는 필수이다.")
    @Test
    void keywordIsMandatory() {
        // given
        SearchBlogRequestBody request = new SearchBlogRequestBody(null, null, null);

        // then
        Set<ConstraintViolation<SearchBlogRequestBody>> violations = validator.validate(request);

        // then
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("키워드를 입력하세요.");
        });
    }
}
