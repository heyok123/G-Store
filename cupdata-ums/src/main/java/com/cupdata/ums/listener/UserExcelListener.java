package com.cupdata.ums.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cupdata.ums.entity.Role;
import com.cupdata.ums.entity.UserEntity;
import com.cupdata.ums.service.UserService;
import com.cupdata.ums.vo.UserExcelVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Description: 读取excel监听器 批量导入用户
 * Created by Wsork on 2021/2/1 16:31.
 **/
public class UserExcelListener extends AnalysisEventListener<UserExcelVo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExcelListener.class);

    //总量
    private int totalCount = 0;
    //成功量
    private int successCount = 0;
    //重复量
    private int repeatCount = 0;
    //异常量
    private int exceptionCount = 0;
    //角色解析错误
    private int errorRoleFlag = 0;
    //list封装导入的数据
    private List<UserExcelVo> list = new ArrayList<>();
    //防止OOM 每2000条数据进行存储 存完清理list
    private static final int BATCH_COUNT = 2000;
    //异常数据
    private List<UserExcelVo> exceptionList = new ArrayList<>();

    private UserService userService;

    public UserExcelListener(){};
    public UserExcelListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        LOGGER.info("表头信息：{}",headMap);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        LOGGER.error("解析失败，继续解析下一行数据",exception);
        exceptionCount++;
    }

    /**
     * @Description: 一行一行读取excel数据
     * Created by Wsork on 2021/2/1 16:53
     */
    @Override
    public void invoke(UserExcelVo userExcelVo, AnalysisContext analysisContext) {
        totalCount++;
        LOGGER.info("第[{}]行数据解析：{}",analysisContext.readRowHolder().getRowIndex(), JSON.toJSONString(userExcelVo));
        //方式1：用户导入(解析全表)
        importUserData(userExcelVo);
        //方式2：单表分批导入(数据量较大时 防止OOM)
//        list.add(userExcelVo);
//        if (list.size() >= BATCH_COUNT) {
//            saveUserData(list); // 业务与importUserData(UserExcelVo userExcelVo)相同
//            list.clear();
//        }

    }

    /**
     * @Description: 批量导入业务 (此处只是批量上传属性username + userRole,其他相关属性类似处理)
     * Created by Wsork on 2021/2/1 16:56
     */
    private void importUserData(UserExcelVo userExcelVo) {
        if (!StringUtils.isEmpty(userExcelVo.getUserName()) || !StringUtils.isEmpty(userExcelVo.getRoleRemarks())) {
            UserEntity user = new UserEntity();
            //1.用户名校验
            UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>()
                    .eq("username", userExcelVo.getUserName()));
            if (userEntity != null) {
                repeatCount++;
                LOGGER.info("用户名已存在：{}",userExcelVo.getUserName());
            } else {
                //2.其他属性设置
                user.setStatus(1);
                user.setPassword("000000"); // 默认000000 或者可做相应加密解密处理
                user.setCreateTime(new Date());
                // 其他相关属性自行设置...
                // 3.角色解析
                errorRoleFlag = 0;
                List<Integer> roleIds = new ArrayList<>();
                String roleRemarks = userExcelVo.getRoleRemarks();
                LOGGER.info("读取角色信息：{}",roleRemarks);
                if (!StringUtils.isEmpty(roleRemarks)) {
                    String[] roleArray = roleRemarks.split(",");
                    for (String remark : roleArray) {
                        LOGGER.info("用户[{}]角色解析[{}]",userExcelVo.getUserName(),remark);
                        // 通过角色描述获取角色信息
                        Role role = userService.selectRoleByRemark(remark);
                        if (role == null) {
                            errorRoleFlag++;
                            LOGGER.info("用户角色[{}]解析错误！",remark);
                        } else {
                            roleIds.add(role.getId());
                        }
                    }
                    user.setRoleIds(roleIds);
                    if (errorRoleFlag > 0) {
                        exceptionCount++;
                    } else {
                        // 解析角色成功 用户信息落表
                        boolean save = userService.save(userEntity);
                        if (save) {
                            // 添加成功后可继续添加用户其他关联属性
                            successCount++;
                        }
                    }
                } else {
                    exceptionCount++;
                    LOGGER.info("未解析到用户[{}]的角色信息！", userExcelVo.getUserName());
                }
            }
        } else {
            exceptionCount++;
            LOGGER.info("用户信息必填项不能为空！");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        saveUserData(list);
        LOGGER.info("全部解析完成！共有[{}]条数据！导入成功[{}]条数据！导入失败[{}]条数据！[{}]条数据重复！",
                totalCount,successCount,exceptionCount,repeatCount);
    }

    /**
     * @Description: 返回导入结果
     * Created by Wsork on 2021/2/1 16:53
     */
    public Map<String,Object> getData(){
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("successCount",successCount);
        map.put("exceptionCount",exceptionCount);
        map.put("repeatCount",repeatCount);
        return map;
    }
}
