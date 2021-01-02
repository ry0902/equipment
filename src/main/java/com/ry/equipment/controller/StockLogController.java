package com.ry.equipment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.equipment.model.entity.StockLog;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.StockLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockLog")
@Api("入库日志相关接口")
public class StockLogController {

    @Autowired
    private StockLogService stockLogService;

    @ApiOperation("得到分页入库日志接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true)
    })
    @GetMapping("/getStockLogs")
    public Response getLogs(Integer currentPage, Integer pageSize){
        try {
            IPage<StockLog> page = new Page<>();
            page.setCurrent(currentPage);
            page.setSize(pageSize);
            IPage<StockLog> iPage = stockLogService.page(page);
            return Response.success(iPage);
        } catch (RuntimeException e){
            return Response.error("查询失败");
        }
    }
}
