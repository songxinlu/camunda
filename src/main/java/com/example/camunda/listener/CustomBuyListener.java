package com.example.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 用户购物监听器
 * @author: songxl
 * @time: 2022/4/27 17:39
 */
public class CustomBuyListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        String eventName = delegateExecution.getEventName();
        Map<String,Object> paramMap = delegateExecution.getProcessInstance().getVariables();
        paramMap.put("executeStatus","1");

    }
}
