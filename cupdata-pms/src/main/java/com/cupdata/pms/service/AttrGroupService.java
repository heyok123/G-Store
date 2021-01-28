package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrGroupEntity;
import com.cupdata.pms.vo.GroupVo;

import java.util.List;

/**
 * 属性分组
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: 根据三级分类id查询分组及组下的规格参数
     * @Author: Wsork
     * @Date: 2021/1/27 14:49
     * @param: [catId]
     * @return: java.util.List<com.cupdata.pms.vo.GroupVo>
     */
    List<GroupVo> queryByCid(long catId);
}

