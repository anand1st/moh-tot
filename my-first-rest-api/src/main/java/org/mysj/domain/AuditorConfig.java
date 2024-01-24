package org.mysj.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
class AuditorConfig {

    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.of("anand");
    }
}
