package com.cupdata.ums.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 用于excel演示批量导入 加入角色信息
 * Created by Wsork on 2021/2/1 17:16.
 **/
@Data
public class Role {

    private Integer id;
    // 角色名称：systemAdmin/admin/operator
    private String roleName;
    // 角色描述:系统管理员/管理员/操作员
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTiem;

}
