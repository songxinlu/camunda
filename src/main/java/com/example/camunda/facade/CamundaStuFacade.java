package com.example.camunda.facade;

import com.example.camunda.vo.PrintVO;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: songxl
 * @time: 2022/4/22 16:04
 */
@Component
public class CamundaStuFacade {
    //操作流程实例
    private final RuntimeService runtimeService;

    public CamundaStuFacade(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void print(String content) {
        Map<String, Object> printVOMap = new HashMap<>();
        PrintVO printVO = new PrintVO();
        printVO.setContent(content);
        printVOMap.put("print",printVO);
        runtimeService.startProcessInstanceByKey("Process_177xe0k",printVOMap);
    }
}
