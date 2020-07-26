package io.yg.service;

import io.yg.config.CommonConfig;
import io.yg.generate.entity.Article;
import io.yg.util.MarkDown2HtmlWrapper;
import io.yg.util.MarkdownEntity;
import io.yg.util.ShellUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 3:46 下午
 * @info
 */
@Service
public class GenerateResourceServiceImpl implements GenerateResourceService {
    @Autowired
    private CommonConfig commonConfig;

    @Override
    public void generateHtml(File file) {
        try {
            MarkdownEntity markdownEntity = MarkDown2HtmlWrapper.ofFile(file.getAbsolutePath());

            // System.out.println(markdownEntity.toString());


            String html = markdownEntity.toString();


            InputStream beginfile = GenerateResource.class.getResourceAsStream("/blog/static/begin.tmp");


            String bengintext = "";

            byte[] bytes = new byte[1024];


            int length = 0;


            while ((length = beginfile.read(bytes)) != -1) {

                bengintext += new String(bytes, 0, length, "UTF-8");

            }
            beginfile.close();

            String midtext = bengintext + html;


            InputStream endfile = GenerateResource.class.getResourceAsStream("/blog/static/end.tmp");


            String endtext = "";

            byte[] endbytes = new byte[1024];


            int endlength = 0;


            while ((endlength = endfile.read(endbytes)) != -1) {

                endtext += new String(endbytes, 0, endlength, "UTF-8");

            }
            endfile.close();


            String finaltext = midtext + endtext;

            file.getName();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(commonConfig.getNginxpath() + file.getName().substring(0, file.getName().length() - 3) + ".html")));

            bufferedWriter.write(finaltext);


            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void generateIndex() {
        try {
            List<Article> articles = new ArrayList<>();

            File filelist = new File(commonConfig.getGitpath());


            for (File file : filelist.listFiles()) {


                if (file.getName().contains(".md") && !file.getName().contains("index.html") && !file.getName().contains("README.md")) {

                    Long time = file.lastModified();

                    //  System.out.println(file.getName());
                    articles.add(new Article(file.getName(), time));

                    // System.out.println(file.getName());

                }
            }


            String tmp01 = "<a href=\"";
            String tmp02 = "\" class=\"post__link\">\n" +
                    "\n" +
                    "<article class=\"post\">\n" +
                    "<h2 class=\"post__title\">";
            String tmp03 = "</h2>\n" +
                    "</article>\n" +
                    "</a>";


            String tmp = "";

            Collections.sort(articles, (article01, article02) -> {
                if (Integer.valueOf(article01.getName().split("-")[0]) > Integer.valueOf(article02.getName().split("-")[0])) {
                    return -1;
                } else {
                    return 1;
                }
            });
            for (Article article : articles) {

                       /* String tmp000= "Fl.md";
                        String[] split = tmp000.split(".");
                        String s = article.getName().split(".")[0];
                        System.out.println(s);*/


                tmp += tmp01 + "/" + article.getName().split("\\.")[0] + ".html" + tmp02 + article.getName().split("\\.")[0] + tmp03;

                // System.out.println(article.getName());

            }


            InputStream beginfile = GenerateResource.class.getResourceAsStream("/blog/static/begin.tmp");


            String bengintext = "";

            byte[] bytes = new byte[1024];


            int length = 0;


            while ((length = beginfile.read(bytes)) != -1) {

                bengintext += new String(bytes, 0, length, "UTF-8");

            }
            beginfile.close();

            String midtext = bengintext + tmp;


            InputStream endfile = GenerateResource.class.getResourceAsStream("/blog/static/end.tmp");


            String endtext = "";

            byte[] endbytes = new byte[1024];


            int endlength = 0;


            while ((endlength = endfile.read(endbytes)) != -1) {

                endtext += new String(endbytes, 0, endlength, "UTF-8");

            }
            endfile.close();


            String finaltext = midtext + endtext;


            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(commonConfig.getNginxpath() + "index.html")));

            bufferedWriter.write(finaltext);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyCSSResource() {
        try {
            File file = new File(commonConfig.getNginxpath() + "/app/");


            boolean exists = file.exists();

            if (!exists) {

                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/css/app.css"), new File(commonConfig.getNginxpath() + "/css/app.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/css/markdown.css"), new File(commonConfig.getNginxpath() + "/css/markdown.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/FontAwesome.otf"), new File(commonConfig.getNginxpath() + "/fonts/FontAwesome.otf"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.eot"), new File(commonConfig.getNginxpath() + "/fonts/fontawesome-webfont.eot"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.svg"), new File(commonConfig.getNginxpath() + "/fonts/fontawesome-webfont.svg"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.ttf"), new File(commonConfig.getNginxpath() + "/fonts/fontawesome-webfont.ttf"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.woff"), new File(commonConfig.getNginxpath() + "/fonts/fontawesome-webfont.woff"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.woff2"), new File(commonConfig.getNginxpath() + "/fonts/fontawesome-webfont.woff2"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void copyImage() {
        File file = new File(commonConfig.getGitpath() + "/assets");

        if (file.exists()) {
            try {
                FileUtils.copyDirectory(file, new File(commonConfig.getNginxpath() + "/assets"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("没有图片，无需拷贝。");
        }
    }

    @Override
    public void execGenerate() {


        File nginxDir = new File(commonConfig.getNginxpath());
        if (!nginxDir.exists()) {
            System.out.println("没有找到 html 路径");

            nginxDir.mkdirs();
        }

        File gitDir = new File(commonConfig.getGitpath());

        if (!gitDir.exists()) {
            System.out.println("没有找到 git 仓库,开始克隆....");

            String gitcloneMsg = ShellUtil.exceScript("git clone " + commonConfig.getGiturl() + " " + commonConfig.getGitpath(), ShellUtil.LINUX);
            System.out.println("克隆信息：" + gitcloneMsg);
        }


        ShellUtil.exceScript("rm -rf " + commonConfig.getNginxpath() + "*", ShellUtil.LINUX);

        String currentPath = ShellUtil.exceScript("pwd", ShellUtil.LINUX);

        String gitMsg = ShellUtil.exceScript("git pull " + currentPath + "/" + commonConfig.getGitpath() + "  master ", ShellUtil.LINUX);

        System.out.println("git 输出信息：" + gitMsg);


        //Thread.sleep(10000);


        this.copyCSSResource();

        this.generateIndex();
        this.copyImage();

        File file = new File(commonConfig.getGitpath());
        File[] listFiles = file.listFiles();
        for (File file1 : listFiles) {
            if (file1.isFile()) {

                if (file1.getName().endsWith(".md") && !file1.getName().equals("README.md")) {
                    System.out.println(file1.getName());
                    this.generateHtml(file1);
                }
            }
        }
    }
}
