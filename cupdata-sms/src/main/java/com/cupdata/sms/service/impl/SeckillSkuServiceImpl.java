package com.cupdata.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.sms.mapper.SeckillSkuMapper;
import com.cupdata.sms.entity.SeckillSkuEntity;
import com.cupdata.sms.service.SeckillSkuService;


@Service("seckillSkuService")
public class SeckillSkuServiceImpl extends ServiceImpl<SeckillSkuMapper, SeckillSkuEntity> implements SeckillSkuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SeckillSkuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SeckillSkuEntity>()
        );

        return new PageResultVo(page);
    }

}