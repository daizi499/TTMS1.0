package xupt.se.ttms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("xupt.se.ttms.mapper")
public class MyBatisPlusConfig {
}
