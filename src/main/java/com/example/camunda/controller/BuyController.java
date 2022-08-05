package com.example.camunda.controller;

import com.example.camunda.dto.GetProcessDiagramListDTO;
import com.example.camunda.entity.ZzDepartment;
import com.example.camunda.facade.BuyFacade;
import com.example.camunda.vo.GetProcessDiagramListVO;
import io.swagger.annotations.Api;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author songxl
 */
@RestController
@RequestMapping("/buy")
@Api(value = "购物管理API", tags = {"购物管理API"})
public class BuyController {
    @Autowired
    BuyFacade buyFacade;

    @PostMapping(value = "/pay")
    public void pay(@RequestParam Double money) {
        ProcessInstance processInstance = buyFacade.pay(money);
        System.out.println(processInstance);
    }

    @PostMapping(value = "/getProcessDiagramList")
    public List<GetProcessDiagramListDTO> getProcessDiagramList(@RequestBody GetProcessDiagramListVO getProcessDiagramListVO) {
        return buyFacade.getProcessDiagramList(getProcessDiagramListVO);
    }
}
