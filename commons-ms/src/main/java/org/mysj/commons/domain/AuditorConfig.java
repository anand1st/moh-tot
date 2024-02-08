package org.mysj.commons.domain;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
@ConditionalOnClass(EntityManagerFactory.class)
class AuditorConfig {

    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.of("anand");
    }
}
