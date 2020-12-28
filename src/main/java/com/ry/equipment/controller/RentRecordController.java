package com.ry.equipment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.equipment.model.entity.RentRecord;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.EquipmentService;
import com.ry.equipment.service.RentRecordService;
import com.ry.equipment.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController
@RequestMapping("/rentRecord")
public class RentRecordController {

    @Autowired
    private RentRecordService rentRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation("得到分页租借记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true),
            @ApiImplicitParam(name = "keyword", value = "多条件查询关键字", required = true),
    })
    @GetMapping("/getRentList")
    public Response getRentList(Integer currentPage, Integer pageSize, String keyword){
        try {
            if(currentPage == null && pageSize == null && keyword == null){
                List<RentRecord> list = rentRecordService.list();
                setUserAndEquip(list);
                return Response.success(list);
            }
            if(keyword.equals("")){
                IPage<RentRecord> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<RentRecord> iPage = rentRecordService.page(page);
                setUserAndEquip(iPage.getRecords());
                return Response.success(iPage);
            }
            else {
                QueryWrapper<RentRecord> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda()
                        .eq(RentRecord::getUserId, keyword);

                IPage<RentRecord> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<RentRecord> iPage = rentRecordService.page(page, queryWrapper);
                setUserAndEquip(iPage.getRecords());
                return Response.success(iPage);
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    public void setUserAndEquip(List<RentRecord> list){
        list.forEach(rentRecord -> {
            rentRecord.setUser(userService.getById(rentRecord.getUserId()));
            rentRecord.setEquipment(equipmentService.getById(rentRecord.getEquipId()));
        });
    }
}
