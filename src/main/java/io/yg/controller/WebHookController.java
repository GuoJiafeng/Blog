package io.yg.controller;

import io.yg.generate.entity.WebHookRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 11:44 上午
 * @info
 */
@Controller
@RequestMapping("/v1")
public class WebHookController {

    @PostMapping("/getNotice")
    public @ResponseBody
    WebHookRequest getNotice(@RequestParam WebHookRequest webHookRequest) {

        System.out.println(webHookRequest.toString());
        return webHookRequest;
    }
}
