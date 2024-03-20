package my.project.kargotakipsistemi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurity {

    private final JpaUserDetailService jpaUserDetailService;

    public WebSecurity(JpaUserDetailService jpaUserDetailService) {
        this.jpaUserDetailService = jpaUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.httpBasic(
                        Customizer.withDefaults()
                )
                .userDetailsService(jpaUserDetailService)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->

                        {
                            String[] resources = new String[]{
                                    "/", "/static", "/home", "/pictureCheckCode", "/include/**",
                                    "/css/**", "/icons/**", "/images/**", "/js/**", "/layer/**"
                            };
                            authorizationManagerRequestMatcherRegistry.requestMatchers(resources).permitAll()
                                    .requestMatchers("signup").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .cors(corsCustomizer -> {
                    corsCustomizer.configurationSource(httpServletRequest -> {
                        var cors = new CorsConfiguration();
                        cors.setAllowedOrigins(List.of("*"));
                        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                        cors.setAllowedHeaders(List.of("*"));
                        return cors;
                    });
                })
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer.loginPage("/login")
                                .loginProcessingUrl("/login").permitAll()
                                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {

                                    if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_EMPLOYEE")))
                                        httpServletResponse.sendRedirect("/shipment/management/shipments");

                                    else if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_CUSTOMER")))
                                        httpServletResponse.sendRedirect("/shipment/customer/home");

                                    else if (authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN")))
                                        httpServletResponse.sendRedirect("/shipment/admin/users");

                                    else
                                        httpServletResponse.sendRedirect("/login");

                                }).failureHandler((httpServletRequest, httpServletResponse, e) -> {
                                    httpServletResponse.sendRedirect("/login");
                                })
                )
                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
