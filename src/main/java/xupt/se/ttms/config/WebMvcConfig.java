package xupt.se.ttms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * Web MVC 配置：页面路由 + 上传文件访问映射
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/admin/studio").setViewName("forward:/admin/studio/index-vue.html");
    }

    /**
     * 把 /uploads/** URL 映射到本地磁盘的上传目录
     * 这样上传的图片就能通过 http://localhost:8080/uploads/xxx.jpg 访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 确保目录以 / 结尾
        String dir = uploadDir.endsWith(File.separator) ? uploadDir : uploadDir + File.separator;
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + dir);
    }
}
