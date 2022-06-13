package com.example.camunda.facade;

import com.example.camunda.dto.GetProcessDiagramListDTO;
import com.example.camunda.vo.GetProcessDiagramListVO;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.TaskQueryImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: songxl
 * @time: 2022/4/22 16:04
 */
@Component
public class BuyFacade {

    @Autowired
    private RuntimeService runtimeService;//操作流程实例服务
    @Autowired
    private IdentityService identityService;//操作用户、操作组服务
    @Autowired
    private RepositoryService repositoryService;//操作流程定义服务
    @Autowired
    private TaskService taskService;//操作服务
    @Autowired
    private FormService formService;//表单服务


    public ProcessInstance pay(Double money) {
        Map<String, Object> inMap = new HashMap<>();
        inMap.put("count", money);
        return runtimeService.startProcessInstanceByKey("Process_1toa8qd", inMap);
    }

    /**
     * 获取进程实例
     *
     * @param getProcessDiagramListVO
     * @Return java.util.List<com.example.camunda.dto.GetProcessDiagramListDTO>
     */
    public List<GetProcessDiagramListDTO> getProcessDiagramList(GetProcessDiagramListVO getProcessDiagramListVO) {
        TaskQuery taskQuery1 = new TaskQueryImpl();
        return null;
    }
}
