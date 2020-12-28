package com.ry.equipment.service;

import com.ry.equipment.model.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */
public interface LogService extends IService<Log> {
    void insert(Object o);

    void Update(Object from, Object to);

    void delete(Object o);
}
