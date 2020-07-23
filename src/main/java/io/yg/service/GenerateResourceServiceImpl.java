package io.yg.service;

import io.yg.config.CommonConfig;
import io.yg.generate.entity.Article;
import io.yg.util.MarkDown2HtmlWrapper;
import io.yg.util.MarkdownEntity;
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

            Collections.sort(articles, new Comparator<Article>() {
                @Override
                public int compare(Article article01, Article article02) {
                    if (article01.getTime() > article02.getTime()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            for (Article article : articles) {

                       /* String tmp000= "Fl.md";
                        String[] split = tmp000.split(".");
                        String s = article.getName().split(".")[0];
                        System.out.println(s);*/


                tmp += tmp01 + "/" + article.getName().split("\\.")[0] + ".html" + tmp02 + article.getName().split("\\.")[0] + tmp03;

                System.out.println(article.getName());

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

                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/css/app.css"), new File("/usr/share/nginx/html/css/app.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/css/markdown.css"), new File("/usr/share/nginx/html/css/markdown.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/FontAwesome.otf"), new File("/usr/share/nginx/html/fonts/FontAwesome.otf"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.eot"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.eot"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.svg"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.svg"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.ttf"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.ttf"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.woff"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.woff"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.woff2"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.woff2"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void copyImage() {
        File file = new File(commonConfig.getImagepath());
        try {
            FileUtils.copyFile(file, new File(commonConfig.getNginxpath() + "/assets/" + file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
