package sample.kdohyeon.blog.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(
        auditorAwareRef = "requestedByAuditAware"
)
@Configuration
public class JpaAuditConfig {
}
