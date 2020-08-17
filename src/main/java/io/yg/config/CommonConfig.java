package io.yg.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 3:48 下午
 * @info
 */
@Data
@Component
@Configuration
@PropertySource("{classpath:application.yml}")
public class CommonConfig {
    @Value("${io.gjf.gitpath}")
    private String gitpath;
    @Value("${io.gjf.nginxpath}")
    private String nginxpath;
    @Value("${io.gjf.giturl}")
    private String giturl;
}
