package io.yg.controller;


import io.yg.generate.entity.WebHookRequest;
import io.yg.service.GenerateResourceService;
import io.yg.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author v_guojiafeng
 * @time 2020/7/23 11:44 上午
 * @info
 */
@RestController
@RequestMapping("/v1")
public class WebHookController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GenerateResourceService generateResourceService;


    @PostMapping("/getNotice")
    public WebHookRequest getNotice(@RequestBody WebHookRequest webHookRequest, @RequestHeader HttpHeaders httpHeaders) throws Exception {

        System.out.println("检验为：" + request.getHeader("X-Hub-Signature"));

        System.out.println("目前收到了" + webHookRequest.toString());

        System.out.println("当前目录为："+ShellUtil.exceScript("pwd", ShellUtil.LINUX));
        System.out.println("打印的消息为："+ShellUtil.exceScript("echo ~/html/", ShellUtil.LINUX));

        generateResourceService.execGenerate();

        return webHookRequest;
    }
}
