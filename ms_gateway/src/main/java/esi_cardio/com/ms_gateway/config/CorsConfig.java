package esi_cardio.com.ms_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class CorsConfig {




        @Bean
        public WebMvcConfigurer corsConfigurer(){
            return new WebMvcConfigurer(){
                public void addCorsMappings(CorsRegistry registry){
                    registry.addMapping("/**")
                            .allowedOrigins("http://localhost:3000/**")
                            .allowedMethods("POST","GET","PUT", "DELETE")
                            .allowedHeaders("*")
                            .allowCredentials(false).maxAge(3600);
                }
            };
        }

    }

