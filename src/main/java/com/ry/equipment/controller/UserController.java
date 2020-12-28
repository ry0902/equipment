package com.ry.equipment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.equipment.model.entity.Equipment;
import com.ry.equipment.model.entity.RentRecord;
import com.ry.equipment.model.entity.ReturnRecord;
import com.ry.equipment.model.entity.User;
import com.ry.equipment.model.vo.Response;
import com.ry.equipment.service.*;
import com.ry.equipment.utils.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */

@RestController
@RequestMapping("/user")
@Api("用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RentRecordService rentRecordService;

    @Autowired
    private ReturnRecordService returnRecordService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private LogService logService;

    private Integer userId;

    @ApiOperation("注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true),
    })
    @PostMapping("/register")
    public Response register(User user){
        try {
            Assert.assertNotNull(user.getUsername(), user.getPassword(), user.getPhone());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(User::getUsername, user.getUsername());
            User user_db = userService.getOne(queryWrapper);
            if(user_db == null){
                user.setRole("user");
                userService.save(user);
                return Response.success("注册成功");
            }
            else {
                return Response.error("该账户已被注册");
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("普通用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/user_login")
    public Response login(String username, String password){
        try {
            Assert.assertNotNull(username, password);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(User::getUsername, username);
            User user_db = userService.getOne(queryWrapper);
            if(user_db == null){
                return Response.error("用户不存在，请先注册！");
            }
            if(user_db.getPassword().equals(password)){
                this.userId = user_db.getId();
                return Response.success("登陆成功！");
            }
            else {
                return Response.error("用户名或密码错误！");
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/admin_login")
    public Response admin_login(String username, String password){
        try {
            Assert.assertNotNull(username, password);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(User::getUsername, username);
            User user_db = userService.getOne(queryWrapper);
            if(!user_db.getRole().equals("admin")){
                return Response.error("您没有权限登录！");
            }
            if(user_db == null){
                return Response.error("用户不存在，请先注册！");
            }
            if(user_db.getPassword().equals(password)){
                return Response.success("登陆成功！");
            }
            else {
                return Response.error("用户名或密码错误！");
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("得到分页用户列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true),
            @ApiImplicitParam(name = "keyword", value = "多条件查询关键字", required = true),
    })
    @GetMapping("/getUserList")
    public Response getUserList(Integer currentPage, Integer pageSize, String keyword){
        try {
            if(currentPage == null && pageSize == null && keyword == null){
                return Response.success(userService.list());
            }
            if(keyword.equals("")){
                IPage<User> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                return Response.success(userService.page(page));
            }
            else {
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().like(User::getUsername, keyword);
                queryWrapper.lambda().or().like(User::getPhone, keyword);
                IPage<User> page = new Page<>();
                page.setCurrent(currentPage);
                page.setSize(pageSize);
                return Response.success(userService.page(page, queryWrapper));
            }
        } catch (RuntimeException e){
            return Response.error("查询失败");
        }
    }

    @ApiOperation("根据用户id获取用户接口")
    @GetMapping("/getUserById")
    public Response getUser(Integer userId){
        try {
            Assert.assertNotNull(userId);
            return Response.success(userService.getById(userId));
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/updatePassword")
    public Response changePassword(Integer userId, String password){
        try {
            User user_db = userService.getById(userId);
            User user_db_old = user_db;
            if(user_db == null){
                return Response.error("没有这个用户id");
            }
            else {
                user_db.setPassword(password);
                logService.Update(user_db_old, user_db);
                userService.updateById(user_db);
                return Response.success("修改成功");
            }
        } catch (RuntimeException e){
            return Response.success(e.getMessage());
        }
    }

    @ApiOperation("用户租借器材")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "equipId", value = "器材id", required = true),
            @ApiImplicitParam(name = "count", value = "租借数量", required = true),
            @ApiImplicitParam(name = "timeBegin", value = "预约的起始时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "预约的归还时间", required = true)
    })
    @PostMapping("/rentEquip")
    public Response rentEquip(Integer equipId, Integer count, String timeBegin, String endTime){
        try {
            Assert.assertNotNull(equipId, count, timeBegin, endTime);
            Equipment equipment = equipmentService.getById(equipId);
            if(equipment.getIniCount() - equipment.getRentCount() >= count){
                Equipment equipment_old = equipment;
                equipment.setRentCount(equipment.getRentCount() + count);
                equipmentService.updateById(equipment);
                logService.Update(equipment_old, equipment);
                RentRecord rentRecord = new RentRecord(this.userId, equipId, count, timeBegin, endTime);
                logService.insert(rentRecord);
                return Response.success(rentRecordService.save(rentRecord));
            }
            else {
                return Response.error("库存不足!");
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }

    @ApiOperation("归还器材接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rentId", value = "租借记录id", required = true)
    })
    @PostMapping("/returnEquip")
    public Response returnEquip(Integer rentId){
        try {
            Assert.assertNotNull(rentId);
            RentRecord rentRecord = rentRecordService.getById(rentId);
            if(rentRecord != null){
                Equipment equipment = equipmentService.getById(rentRecord.getEquipId());
                Equipment equipment_old = equipment;
                equipment.setRentCount(equipment.getRentCount() - rentRecord.getCount());
                equipmentService.updateById(equipment);
                logService.Update(equipment_old, equipment);
                rentRecordService.removeById(rentId);
                logService.delete(rentId);
                ReturnRecord returnRecord = new ReturnRecord(rentRecord.getUserId(), rentRecord.getEquipId(), rentRecord.getCount());
                logService.insert(returnRecord);
                returnRecordService.save(returnRecord);
                return Response.success("归还成功");
            }
            else {
                return Response.error("没有对应租借记录");
            }
        } catch (RuntimeException e){
            return Response.error(e.getMessage());
        }
    }
}
