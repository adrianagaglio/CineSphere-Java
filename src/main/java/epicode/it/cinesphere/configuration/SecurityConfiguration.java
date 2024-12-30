package epicode.it.cinesphere.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors() // Abilita CORS
                .and()
                .csrf().disable() // Disabilita CSRF
                .authorizeRequests()
                .anyRequest().permitAll(); // Permetti tutte le richieste (configurazione di base)

        return http.build();
    }
}
