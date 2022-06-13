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
@TableName("zz_role")
public class ZzRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职务ID
     */
    @TableId("role_id")
    private String roleId;

    /**
     * 职务名称
     */
    @TableField("role_name")
    private String roleName;

}