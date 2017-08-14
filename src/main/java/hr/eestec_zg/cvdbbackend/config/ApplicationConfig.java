package hr.eestec_zg.cvdbbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hr.eestec_zg.cvdb")
public class ApplicationConfig {
}
