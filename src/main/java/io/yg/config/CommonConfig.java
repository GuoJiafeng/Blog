package io.yg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 3:48 下午
 * @info
 */
@Data
@Component
@ConfigurationProperties(prefix = "io.gjf")
public class CommonConfig {
    private String gitpath;
    private String nginxpath;
    private String giturl;
    private String imagepath;
}
