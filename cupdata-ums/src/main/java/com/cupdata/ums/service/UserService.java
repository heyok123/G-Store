package com.cupdata.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.ums.entity.Role;
import com.cupdata.ums.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 用户表
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:32:01
 */
public interface UserService extends IService<UserEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: 用户批量导入
     * @Author: Wsork
     * @Date: 2021/2/1 16:31
     * @param: [file, userService]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> batchImportUser(MultipartFile file, UserService userService);

    /**
     * @Description: 通过角色描述获取用户角色
     * @Author: Wsork
     * @Date: 2021/2/1 17:21
     * @param: [remark]
     * @return: com.cupdata.ums.entity.Role
     */
    Role selectRoleByRemark(String remark);
}

