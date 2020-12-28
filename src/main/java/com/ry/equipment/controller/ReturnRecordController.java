package com.ry.equipment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.equipment.model.entity.RentRecord;
import com.ry.equipment.model.entity.ReturnRecord;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.EquipmentService;
import com.ry.equipment.service.RentRecordService;
import com.ry.equipment.service.ReturnRecordService;
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
@RequestMapping("/returnRecord")
public class ReturnRecordController {
    @Autowired
    private ReturnRecordService returnRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation("得到分页归还记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true),
            @ApiImplicitParam(name = "keyword", value = "多条件查询关键字", required = true),
    })
    @GetMapping("/getReturnList")
    public Response getReturnList(Integer currentPage, Integer pageSize, String keyword){
        try {
            if(currentPage == null && pageSize == null && keyword == null){
                List<ReturnRecord> list = returnRecordService.list();
                setUserAndEquip(list);
                return Response.success(list);
            }
            if(keyword.equals("")){
                IPage<ReturnRecord> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<ReturnRecord> iPage = returnRecordService.page(page);
                setUserAndEquip(iPage.getRecords());
                return Response.success(iPage);
            }
            else {
                QueryWrapper<ReturnRecord> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda()
                        .eq(ReturnRecord::getUserId, keyword);

                IPage<ReturnRecord> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<ReturnRecord> iPage = returnRecordService.page(page, queryWrapper);
                setUserAndEquip(iPage.getRecords());
                return Response.success(iPage);
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    public void setUserAndEquip(List<ReturnRecord> list){
        list.forEach(returnRecord -> {
            returnRecord.setUser(userService.getById(returnRecord.getUserId()));
            returnRecord.setEquipment(equipmentService.getById(returnRecord.getEquipId()));
        });
    }
}
