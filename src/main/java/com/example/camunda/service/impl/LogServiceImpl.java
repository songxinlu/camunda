package com.example.camunda.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.camunda.entity.Log;
import com.example.camunda.mapper.LogMapper;
import com.example.camunda.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Service实现类
 * </p>
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}