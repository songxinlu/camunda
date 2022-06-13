package com.example.camunda.controller;

import com.example.camunda.api.CommonResult;
import com.example.camunda.facade.DeployFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author songxl
 */
@RestController
@RequestMapping("/deploy")
@Api(value = "流程部署API", tags = { "流程部署API" })
public class DeployController {
    @Autowired
    DeployFacade deployFacade;

    @PostMapping(value = "/deployByResource")
    @ApiOperation(value = "通过名称部署流程[by:songxl]",
            notes = "resource: bpmn文件名")
    public CommonResult<Boolean> deployByResource(String name, String resource) {
        return CommonResult.success(deployFacade.deployByResource(name, resource));
    }

    @PostMapping(value = "/deployByStringPath")
    @ApiOperation(value = "通过文本部署流程[by:songxl]",
            notes = "resource: bpmn文件名\r\n" + "path:bpmn文件路径")
    public CommonResult<Boolean> deployByStringPath(String name, String resource, String path) {
        return CommonResult.success(deployFacade.deployByStringPath(name, resource, path));
    }

    @PostMapping(value = "/deployByInputStream")
    @ApiOperation(value = "通过输入流部署流程[by:songxl]",
            notes = "resource: bpmn文件名\r\n" + "path:bpmn文件路径")
    public CommonResult<Boolean> deployByInputStream(String name, String resource, String path) {
        return CommonResult.success(deployFacade.deployByInputStream(name, resource, path));
    }
}
