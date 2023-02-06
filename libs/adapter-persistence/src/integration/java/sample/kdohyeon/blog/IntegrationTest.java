package sample.kdohyeon.blog;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import sample.kdohyeon.blog.configure.PersistenceJpaConfig;
import sample.kdohyeon.blog.persistence.PersistenceModule;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ComponentScan(basePackageClasses = {PersistenceModule.class})
@ContextConfiguration(classes = {PersistenceJpaConfig.class})
public abstract class IntegrationTest {
}
