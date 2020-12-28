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
@ApiModel(value="ReturnRecord对象", description="")
public class ReturnRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer equipId;

    private Integer count;

    private Date timeReturn;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Equipment equipment;

    public ReturnRecord(Integer userId, Integer equipId, Integer count) {
        this.userId = userId;
        this.equipId = equipId;
        this.count = count;
        this.timeReturn = new Date();
    }
}
