package com.example.camunda.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description:
 * @author: songxl
 * @time: 2022/4/24 14:30
 */
@Getter
@Setter
public class PrintVO implements Serializable {
    private static final long serialVersionUID = -6671923016271061518L;
    private String content;
}
