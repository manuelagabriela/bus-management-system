package ClientBusManagement.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Conf implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/webjars/")
                .addResourceLocations("/webjars/")
                .resourceChain(false);

        registry.addResourceHandler("/")
                .addResourceLocations("classpath:/static/");
    }
}
