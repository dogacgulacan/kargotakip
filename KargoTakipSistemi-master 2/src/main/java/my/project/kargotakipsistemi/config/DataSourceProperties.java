package my.project.kargotakipsistemi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
}
