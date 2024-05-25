package cloud_computing.image_tag_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${client.host.address}")
    private String client_host_address;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(client_host_address)
                .allowedMethods("POST")
                .allowedOrigins("Authorization", "Content-type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
