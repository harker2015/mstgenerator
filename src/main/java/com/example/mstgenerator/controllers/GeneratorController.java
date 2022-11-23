package com.example.mstgenerator.controllers;

import com.example.mstgenerator.common.ResultJson;
import com.example.mstgenerator.models.ProjectInfo;
import com.example.mstgenerator.services.GeneratorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/generator")
public class GeneratorController {

    @Resource
    private GeneratorService generatorService;

    @RequestMapping("/generate")
    public Object generate(ProjectInfo projectInfo){
        generatorService.generate(projectInfo);
        return ResultJson.success();
    }
}