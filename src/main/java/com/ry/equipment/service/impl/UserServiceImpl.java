package com.ry.equipment.service.impl;

import com.ry.equipment.model.entity.User;
import com.ry.equipment.mapper.UserMapper;
import com.ry.equipment.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
