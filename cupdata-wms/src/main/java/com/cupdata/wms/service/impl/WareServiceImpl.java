package com.cupdata.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.wms.entity.WareEntity;
import com.cupdata.wms.mapper.WareMapper;
import com.cupdata.wms.service.WareService;
import org.springframework.stereotype.Service;


@Service("wareService")
public class WareServiceImpl extends ServiceImpl<WareMapper, WareEntity> implements WareService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<WareEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<WareEntity>()
        );

        return new PageResultVo(page);
    }

}