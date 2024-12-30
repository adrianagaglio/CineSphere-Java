package epicode.it.cinesphere.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.setAllowCredentials(true); // Permetti l'invio di credenziali (opzionale)
        config.addAllowedOrigin("http://localhost:4200"); // Origine consentita
        config.addAllowedHeader("*"); // Permetti tutti gli header
        config.addAllowedMethod("*"); // Permetti tutti i metodi HTTP (GET, POST, PUT, DELETE, ecc.)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
