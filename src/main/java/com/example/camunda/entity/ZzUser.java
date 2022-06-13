package com.example.camunda.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  实体类
 * </p>
 */
@Data
@TableName("zz_user")
public class ZzUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("user_id")
    private String userId;

    /**
     * 部门ID
     */
    @TableField("department_id")
    private String departmentId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("psd")
    private String psd;

}