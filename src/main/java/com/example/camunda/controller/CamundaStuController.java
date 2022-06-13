package com.example.camunda.controller;

import com.example.camunda.facade.CamundaStuFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Description:
 * @Author songxl
 * @Date 2021/10/26
 */
@RestController
@RequestMapping("/camunda")
@Api(value = "打印服务API", tags = {"打印服务API"})
public class CamundaStuController {
    @Autowired
    CamundaStuFacade camundaStuFacade;

    @ApiOperation(value = "打印服务[by:songxl]",
            notes = "")
    @PostMapping("/print")
    public void print(@RequestParam String content) {
        camundaStuFacade.print(content);
    }
}
