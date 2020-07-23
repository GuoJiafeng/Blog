package io.yg.controller;

import io.yg.generate.entity.WebHookRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 11:44 上午
 * @info
 */
@RestController
@RequestMapping("/v1")
public class WebHookController {

    @PostMapping("/getNotice")
    public WebHookRequest getNotice(@RequestBody WebHookRequest webHookRequest) {
        System.out.println("目前收到了" + webHookRequest.toString());
        return webHookRequest;
    }
}
