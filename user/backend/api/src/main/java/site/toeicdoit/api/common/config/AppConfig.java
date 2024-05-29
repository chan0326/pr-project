package site.toeicdoit.api.common.config;


import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
    String apiKey = "2474726887526877";
    String secretKey = "Qnoq0XDh0hEvuKgGeMBxurOCEaqoIYfx9UxbQ9qGJMsJccATHUBNraX0BiFq58DhbLPSYZW7T9VXehnO";

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
