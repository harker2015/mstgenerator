package com.example.mstgenerator.services.impl;

import com.example.mstgenerator.models.ProjectInfo;
import com.example.mstgenerator.services.GeneratorService;
import com.example.mstgenerator.utils.FreemarkerUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorServiceImpl implements GeneratorService {
    Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);

    @Resource
    private FreemarkerUtil freemarkerUtil;

    @Value("${filePath}")
    private String filePath;

    @Override
    public void generate(ProjectInfo projectInfo) {
        logger.info("Generate begin!");

        Map<String, Object> params = new HashMap<>();

        List<String> users = new ArrayList<>();
        users.add("Joe");
        users.add("Kate");
        users.add("Fred");

        params.put("users", users);


        try {
            String result = freemarkerUtil.processString("test.ftl", params);
            logger.info("result:" + result);
            FileUtils.write(new File(filePath + "/test.java"), result, StandardCharsets.UTF_8, false);
        } catch (Exception e) {
            logger.error(e.getMessage());
        };
    }
}
