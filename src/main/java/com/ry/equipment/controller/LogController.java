package com.ry.equipment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.equipment.model.entity.Log;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.LogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation("得到分页日志接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true)
    })
    @GetMapping("/getLogs")
    public Response getLogs(Integer currentPage, Integer pageSize){
        try {
                IPage<Log> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                IPage<Log> iPage = logService.page(page);
                return Response.success(iPage);
        } catch (RuntimeException e){
            return Response.error("查询失败");
        }
    }
}
