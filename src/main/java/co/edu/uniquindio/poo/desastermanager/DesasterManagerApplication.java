package co.edu.uniquindio.poo.desastermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DesasterManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesasterManagerApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);

                registry.addMapping("/rutas/**")
                        .allowedOrigins("http://localhost:63342")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

                registry.addMapping("/evacuaciones/**")
                        .allowedOrigins("http://localhost:63342")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

            }
        };
    }

}
