package com.ry.equipment.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StockLog对象", description="")
public class StockLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer equipId;

    private Integer count;

    private BigDecimal cost;

    private Date createTime;

    public StockLog(Integer equipId, Integer count, BigDecimal cost) {
        this.equipId = equipId;
        this.count = count;
        this.cost = cost;
        this.createTime = new Date();
    }
}
