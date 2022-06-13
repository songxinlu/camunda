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
@TableName("zz_department")
public class ZzDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId("department_id")
    private String departmentId;

    /**
     * 部门名称
     */
    @TableField("department_name")
    private String departmentName;

}