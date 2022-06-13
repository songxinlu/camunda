package com.example.camunda.facade.base;

import com.example.camunda.service.impl.LogServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 表名：z_log 业务类
 * </p>
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogFacade extends LogServiceImpl {

}