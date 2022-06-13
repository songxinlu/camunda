package com.example.camunda.facade.base;

import com.example.camunda.service.impl.ZzUserServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 表名：zz_user 业务类
 * </p>
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ZzUserFacade extends ZzUserServiceImpl {

}