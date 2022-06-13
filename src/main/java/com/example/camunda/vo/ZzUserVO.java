package com.example.camunda.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhoupeng
 */
@Setter
@Getter
@ToString
public class ZzUserVO {
    private static final long serialVersionUID = 803513297730188794L;


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 职务名称
     */
    private String roleName;
}
