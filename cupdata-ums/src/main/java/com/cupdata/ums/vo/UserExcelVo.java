package com.cupdata.ums.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: TODO
 * Created by Wsork on 2021/2/1 16:25.
 **/
@Data
public class UserExcelVo {

    @ExcelProperty(index = 0,value = "用户名")
    private String userName;

    @ExcelProperty(index = 0,value = "用户角色信息 1:系统管理员 2:管理者 3:操作员")
    private String roleRemarks;

//    @ExcelProperty(index = 0,value = "用户名")
//    private String userName;


}
