package com.ry.equipment.model.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseDataVO<T> extends BaseVO{
    private T data;

    public BaseDataVO(HttpStatus status, T data){
        super(status);
        this.data = data;
    }
}
