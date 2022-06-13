package com.example.camunda.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  实体类
 * </p>
 */
@Data
@TableName("z_paiche")
public class Paiche implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 派车ID
     */
    @TableId("id")
    private String id;

    /**
     * 对应流程实例ID
     */
    @TableField("pid")
    private String pid;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 开始时间
     */
    @TableField("start_date_time")
    private Date startDateTime;

    /**
     * 乘车人数
     */
    @TableField("persons")
    private Integer persons;

    /**
     * 乘车电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 乘车地点
     */
    @TableField("start_position")
    private String startPosition;

    /**
     * 到达目的地
     */
    @TableField("end_position")
    private String endPosition;

    /**
     * 司机姓名
     */
    @TableField("driver")
    private String driver;

    /**
     * 车号
     */
    @TableField("car")
    private String car;

    /**
     * 备注
     */
    @TableField("bzhu")
    private String bzhu;

    /**
     * 记录创建时间
     */
    @TableField("create_time")
    private Date createTime;

}