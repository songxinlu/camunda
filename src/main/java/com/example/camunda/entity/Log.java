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
@TableName("z_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId("id")
    private String id;

    /**
     * 任务名称
     */
    @TableField("task")
    private String task;

    /**
     * 任务ID
     */
    @TableField("task_id")
    private String taskId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 操作步骤,对应zz_code中关键字
     */
    @TableField("isagreed")
    private String isagreed;

    /**
     * 日志记录
     */
    @TableField("log")
    private String log;

    /**
     * 记录创建时间
     */
    @TableField("create_time")
    private Date createTime;

}