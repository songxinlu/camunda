package com.example.camunda.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.camunda.entity.ZzUser;
import com.example.camunda.vo.ZzUserVO;

import java.util.List;

/**
 * <p>
 * 表名：zz_user Mapper接口
 * </p>
 */
public interface ZzUserMapper extends BaseMapper<ZzUser> {

    List<ZzUserVO> list();
}