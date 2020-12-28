package com.ry.equipment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.equipment.model.entity.Equipment;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.CategoryService;
import com.ry.equipment.service.EquipmentService;
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

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */
@Api("器材接口")
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增器材")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材id(有就是更新，无就是新增)", required = true),
            @ApiImplicitParam(name = "equipName", value = "器材名称", required = true),
            @ApiImplicitParam(name = "cateId", value = "种类id", required = true),
            @ApiImplicitParam(name = "rent", value = "器材租金", required = true),
            @ApiImplicitParam(name = "iniCount", value = "器材原始库存", required = true),
            @ApiImplicitParam(name = "rentCount", value = "器材租出去多少件", required = true)
    })
    @PostMapping("/saveOrUpdateEquip")
    public Response saveOrUpdateEquip(Equipment equipment){
        try {
            Assert.assertNotNull(equipment);
            if(equipment.getIniCount() < equipment.getRentCount()){
                return Response.error("借出数不能大于原库存数");
            }
            equipmentService.saveOrUpdate(equipment);
            return Response.success(equipment);
        } catch (Exception e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("得到分页器材列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true),
            @ApiImplicitParam(name = "keyword", value = "多条件查询关键字", required = true),
    })
    @GetMapping("/getEquipList")
    public Response getEquipList(Integer currentPage, Integer pageSize, String keyword){
        try {
            if(currentPage == null && pageSize == null && keyword == null){
                List<Equipment> list = equipmentService.list();
                setCate(list);
                return Response.success(list);
            }
            if(keyword.equals("")){
                IPage<Equipment> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<Equipment> iPage = equipmentService.page(page);
                setCate(iPage.getRecords());
                return Response.success(iPage);
            }
            else {
                QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().like(Equipment::getEquipName, keyword);
                IPage<Equipment> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<Equipment> iPage = equipmentService.page(page, queryWrapper);
                setCate(iPage.getRecords());
                return Response.success(iPage);
            }
        } catch (RuntimeException e){
            return Response.error("查询失败");
        }
    }

    public void setCate(List<Equipment> list){
        list.forEach(equipment -> {
            equipment.setCategory(categoryService.getById(equipment.getCateId()));
        });
    }
}
