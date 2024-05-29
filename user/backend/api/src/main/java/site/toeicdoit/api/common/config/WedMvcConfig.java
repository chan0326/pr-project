package site.toeicdoit.api.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.toeicdoit.api.common.component.intercepter.AuthInterceptor;

@Configuration
@RequiredArgsConstructor
public class WedMvcConfig implements WebMvcConfigurer {

    private  final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")
        .excludePathPatterns("/api/auth/**");
    }
}
