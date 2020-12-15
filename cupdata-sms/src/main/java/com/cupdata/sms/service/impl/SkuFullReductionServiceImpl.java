package com.cupdata.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.sms.mapper.SkuFullReductionMapper;
import com.cupdata.sms.entity.SkuFullReductionEntity;
import com.cupdata.sms.service.SkuFullReductionService;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReductionEntity> implements SkuFullReductionService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SkuFullReductionEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageResultVo(page);
    }

}