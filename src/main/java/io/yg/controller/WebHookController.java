package io.yg.controller;

import io.yg.generate.entity.WebHookRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 11:44 上午
 * @info
 */
@RestController
@RequestMapping("/v1")
public class WebHookController {

    @PostMapping("/getNotice")
    public WebHookRequest getNotice(@RequestBody WebHookRequest webHookRequest, @RequestHeader HttpRequest httpRequest) {

        List<String> list = httpRequest.getHeaders().get("X-Hub-Signature");
        for (String s : list) {
            System.out.println( "密码是："+s);
        }
        System.out.println("目前收到了" + webHookRequest.toString());
        return webHookRequest;
    }
}
