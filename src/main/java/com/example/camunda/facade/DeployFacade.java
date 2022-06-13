package com.example.camunda.facade;

import com.example.camunda.exception.Asserts;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;


/**
 * @Description: 流程部署处理类
 * @author: songxl
 * @time: 2022/4/22 16:04
 */
@Component
@Slf4j
public class DeployFacade {

    @Autowired
    private RepositoryService repositoryService;//操作流程定义服务

    public Boolean deployByResource(String name, String resource) {
       try {
           Deployment deployment = repositoryService.createDeployment()
                   .name(name)
                   .addClasspathResource(resource)
                   .deploy();
           log.info("流程id:" + deployment.getId());
           log.info("流程Name:" + deployment.getName());
       }catch (Exception e){
           Asserts.fail(e.toString());
       }
        return false;
    }


    public Boolean deployByStringPath(String name, String resource, String path) {
        try {
            String file = FileUtils.readFileToString(new File(path), "UTF-8");
            Deployment deployment = repositoryService.createDeployment()
                    .name(name)
                    .addString(resource, file)
                    .deploy();
            log.info("流程id:" + deployment.getId());
            log.info("流程Name:" + deployment.getName());
        } catch (IOException e) {
            e.printStackTrace();
            Asserts.fail("文件不存在");
        }

        return false;
    }

    public Boolean deployByInputStream(String name, String resource, String path) {
        try {
            Deployment deployment = repositoryService.createDeployment()
                    .name(name)
                    .addInputStream(resource, new FileInputStream(path))
                    .deploy();
            log.info("流程id:" + deployment.getId());
            log.info("流程Name:" + deployment.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Asserts.fail("文件不存在");
        }
        return false;
    }
}
