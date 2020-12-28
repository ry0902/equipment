package com.ry.equipment.model.vo;

import com.ry.equipment.model.vo.base.BaseVO;
import org.springframework.http.HttpStatus;

public interface Response {
    static Response success(){
        return pack(HttpStatus.OK);
    }

    static Response success(String msg){
        return pack(HttpStatus.OK.value(), msg);
    }

    static Response success(Object entity){
        return pack(HttpStatus.OK, entity);
    }

    static Response error(){
        return pack(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static Response error(String msg){
        return pack(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    static Response error(Object entity){
        return pack(HttpStatus.INTERNAL_SERVER_ERROR, entity);
    }

    static Response pack(HttpStatus status) {
        return new BaseVO(status);
    }

    static Response pack(Integer status, String msg) {
        return new BaseVO(status, msg);
    }

    @SuppressWarnings("unchecked")
    static Response pack(HttpStatus status, Object data){
        return new DefaultDataVO(status, data);
    }
}
