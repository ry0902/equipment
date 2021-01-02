package com.ry.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.equipment.mapper.StockLogMapper;
import com.ry.equipment.model.entity.StockLog;
import com.ry.equipment.service.StockLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockLogServiceImpl extends ServiceImpl<StockLogMapper, StockLog> implements StockLogService {
}
