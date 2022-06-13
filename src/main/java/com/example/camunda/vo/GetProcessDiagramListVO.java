package com.example.camunda.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @author: songxl
 * @time: 2022/4/28 14:09
 */
@Getter
@Setter
@ToString
@ApiModel(value = "获取进程实例--接口入参")
public class GetProcessDiagramListVO implements Serializable {
    private static final long serialVersionUID = -3124749601946147964L;

    @ApiModelProperty(value = "进程名称")
    private String processName;

}
