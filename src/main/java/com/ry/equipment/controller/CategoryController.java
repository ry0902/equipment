package com.ry.equipment.controller;


import com.ry.equipment.model.entity.Category;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.CategoryService;
import com.ry.equipment.utils.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */
@Api("器材种类接口")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有器材种类")
    @GetMapping("/getCateList")
    public Response getCateList(){
        try {
            return Response.success(categoryService.list());
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("新增种类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "种类id(有就是更新，无就是新增)", required = true),
            @ApiImplicitParam(name = "cateName", value = "种类名称", required = true),
    })
    @PostMapping("/saveOrUpdateCate")
    public Response saveOrUpdateCate(Category category){
        try {
            Assert.assertNotNull(category);
            categoryService.saveOrUpdate(category);
            return Response.success(category);
        } catch (Exception e){
            return Response.error(e.getMessage());
        }
    }
}
