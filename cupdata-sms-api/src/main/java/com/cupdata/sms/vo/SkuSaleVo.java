package com.cupdata.sms.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 营销字段Vo
 * Created by Wsork on 2021/1/28 10:38.
 **/
@Data
public class SkuSaleVo {
    /**
     * 1.积分活动
     *
     * 优惠生效情况[1111（四个状态位，从右到左）;
     * 0 - 无优惠，成长积分是否赠送;
     * 1 - 无优惠，购物积分是否赠送;
     * 2 - 有优惠，成长积分是否赠送;
     * 3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
     */
    private List<Integer> work;
    // 成长积分
    private BigDecimal growBounds;
    // 购物积分
    private BigDecimal buyBounds;

    /**
     * 2.满减活动
     */
    // 满价格
    private BigDecimal fullPrice;
    // 减价格
    private BigDecimal reducePrice;
    // 是否参与其他优惠
    private Integer fullAddOther;

    /**
     * 3.打折活动
     */
    // 满几件
    private Integer fullCount;
    // 打几折
    private BigDecimal discount;
    // 是否叠加其他优惠[0-不可叠加，1-可叠加]
    private Integer addOther;

    /**
     * 4.skuId
     */
    private Long skuId;
}
