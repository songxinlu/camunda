package com.example.camunda.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * @Description: 支付任务
 * @author: songxl
 * @time: 2022/4/18 15:17
 */

public class BuyTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String,Object> t = delegateExecution.getVariables();
        System.out.println(t);
    }
}
