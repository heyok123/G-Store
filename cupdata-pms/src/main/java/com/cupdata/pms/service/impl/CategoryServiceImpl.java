package com.cupdata.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.CategoryEntity;
import com.cupdata.pms.mapper.CategoryMapper;
import com.cupdata.pms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<CategoryEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * @Description: 根据父id查询商品分类
     * @Author: Wsork
     * @Date: 2020/12/29 17:01
     */
    @Override
    public List<CategoryEntity> queryCategory(long parentId) {
        // 构造查询条件
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        // parentId为-1 则没有传此字段 查询所有
        if (parentId != -1){
            wrapper.eq("parent_id",parentId);
        }
        return this.categoryMapper.selectList(wrapper);
    }

}