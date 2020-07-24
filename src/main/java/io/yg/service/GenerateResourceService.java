package io.yg.service;

import java.io.File;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 3:44 下午
 * @info
 */
public interface GenerateResourceService {
    void generateHtml(File file);

    void generateIndex();

    void copyCSSResource();

    void copyImage();

    void execGenerate();
}
