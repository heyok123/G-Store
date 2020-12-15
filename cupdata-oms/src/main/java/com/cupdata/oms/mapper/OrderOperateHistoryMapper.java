package com.cupdata.oms.mapper;

import com.cupdata.oms.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:37:26
 */
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistoryEntity> {
	
}
