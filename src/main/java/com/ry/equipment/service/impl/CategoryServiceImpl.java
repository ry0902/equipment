package com.ry.equipment.service.impl;

import com.ry.equipment.model.entity.Category;
import com.ry.equipment.mapper.CategoryMapper;
import com.ry.equipment.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
