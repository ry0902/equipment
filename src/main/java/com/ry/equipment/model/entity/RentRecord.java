package com.ry.equipment.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ry
 * @since 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RentRecord对象", description="")
public class RentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer equipId;

    private Integer count;

    private String timeBegin;

    private String endTime;

    private Date createTime;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Equipment equipment;

    public RentRecord(Integer userId, Integer equipId, Integer count, String timeBegin, String endTime) {
        this.userId = userId;
        this.equipId = equipId;
        this.count = count;
        this.timeBegin = timeBegin;
        this.endTime = endTime;
        this.createTime = new Date();
    }
}
