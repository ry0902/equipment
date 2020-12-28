package com.ry.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.equipment.model.entity.Log;
import com.ry.equipment.mapper.LogMapper;
import com.ry.equipment.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.equipment.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private IService<Log> iService;

    @Override
    public void insert(Object o) {
        String msg = "INSERT: " + CommonUtils.toString(o);
        Log l = new Log(msg);
        iService.save(l);
    }

    @Override
    public void Update(Object from, Object to) {
        String msg = "UPDATE: from " + CommonUtils.toString(from) + " to " + CommonUtils.toString(to);
        Log l = new Log(msg);
        iService.save(l);
    }

    @Override
    public void delete(Object o) {
        String msg = "DELETE: " + CommonUtils.toString(o);
        Log log = new Log(msg);
        iService.save(log);
    }
}
