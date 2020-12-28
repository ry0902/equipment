package com.ry.equipment.model.vo;

import com.ry.equipment.model.vo.base.BaseDataVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultDataVO extends BaseDataVO<Object> {
    public DefaultDataVO(HttpStatus status, Object data) {
        super(status, data);
    }
}
