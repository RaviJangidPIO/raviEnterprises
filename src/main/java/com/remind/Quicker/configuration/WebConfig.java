package com.remind.Quicker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/files/**").addResourceLocations("classpath:/static/files/");
        registry.addResourceHandler("/static/javascript/**").addResourceLocations("classpath:/static/javascript/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

    }
}
