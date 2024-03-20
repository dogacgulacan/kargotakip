package my.project.kargotakipsistemi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

//Singleton pattern is used here.
@Configuration
public class DatabaseConfig {

    private final DataSourceProperties dataSourceProperties;

    private DataSource dataSource = null;

    public DatabaseConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    private DataSource init() {
        dataSource = DataSourceBuilder.create()
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
        return dataSource;
    }

    @Bean
    public DataSource dataSource() {
        if (dataSource == null) {
            return init();
        }
        return dataSource;
    }
}
