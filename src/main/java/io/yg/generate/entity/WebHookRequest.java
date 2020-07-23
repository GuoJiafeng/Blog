package io.yg.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


/**
 * @author v_guojiafeng
 * @time 2020/7/23 12:57 下午
 * @info
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookRequest  {
    private String ref;
    private String before;
    private String after;

}
