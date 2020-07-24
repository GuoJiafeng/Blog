package io.yg.controller;

import io.yg.config.CommonConfig;
import io.yg.generate.entity.WebHookRequest;
import io.yg.service.GenerateResourceService;
import io.yg.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GenerateResourceService generateResourceService;

    @Autowired
    private CommonConfig commonConfig;

    @PostMapping("/getNotice")
    public WebHookRequest getNotice(@RequestBody WebHookRequest webHookRequest, @RequestHeader HttpHeaders httpHeaders)  throws Exception{

        System.out.println("检验为：" + request.getHeader("X-Hub-Signature"));

        System.out.println("目前收到了" + webHookRequest.toString());


        ShellUtil.exceScript("rm -rf " + commonConfig.getNginxpath() + "*", ShellUtil.LINUX);


        String gitMsg = ShellUtil.exceScript("git pull " + commonConfig.getGitpath() + "  master ", ShellUtil.LINUX);

        System.out.println("git 输出信息："+gitMsg);


        //Thread.sleep(10000);


        generateResourceService.copyCSSResource();

        generateResourceService.generateIndex();
        generateResourceService.copyImage();

        File file = new File(commonConfig.getGitpath());
        File[] listFiles = file.listFiles();
        for (File file1 : listFiles) {
            if (file1.isFile()) {

                if (file1.getName().endsWith(".md") && !file1.getName().equals("README.md")) {
                    System.out.println(file1.getName());
                    generateResourceService.generateHtml(file1);
                }
            }
        }

        return webHookRequest;
    }
}
