package com.cupdata.ums.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.exception.MyException;
import com.cupdata.ums.entity.Role;
import com.cupdata.ums.entity.UserEntity;
import com.cupdata.ums.listener.UserExcelListener;
import com.cupdata.ums.mapper.UserMapper;
import com.cupdata.ums.service.UserService;
import com.cupdata.ums.vo.UserExcelVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<UserEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<UserEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * @Description: 用户批量导入
     * Created by Wsork on 2021/2/1 16:31
     */
    @Override
    public Map<String, Object> batchImportUser(MultipartFile file, UserService userService) {
        UserExcelListener userExcelListener = new UserExcelListener(userService);
        try {
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 读取第一个文件输入流  文件流自动关闭
            EasyExcel.read(inputStream, UserExcelVo.class,userExcelListener).sheet().headRowNumber(1).doRead();
        } catch (IOException e) {
            throw new MyException("用户导入失败");
        }
        return userExcelListener.getData();
    }

    /**
     * @Description: 通过角色描述获取用户角色
     * Created by Wsork on 2021/2/1 17:21
     */
    @Override
    public Role selectRoleByRemark(String remark) {
        Role role = new Role();
        role.setRemark(remark);
        // 此处用于演示批量上传 临时加入角色 查询省略...
        return role;
    }


}