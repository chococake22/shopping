package kr.project.shopping.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 해당 url 접근시 handler가 낚아채간다.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/summernoteImage/**")
                // 파일이 저장되어 있는 부분에 접근함
                .addResourceLocations("file:///Users/ganghyeog/Desktop/AjaxDownload/shop/");
    }
}
