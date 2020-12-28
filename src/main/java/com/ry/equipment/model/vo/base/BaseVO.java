package com.ry.equipment.model.vo.base;

import com.ry.equipment.model.vo.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseVO implements Response {
    private Meta meta;

    public BaseVO(HttpStatus status) {
        this.meta = new Meta(status);
    }

    public BaseVO(Integer status, String msg) {
        this.meta = new Meta(status, msg);
    }

    @Data
    public static class Meta {
        private String msg;
        private Integer status;

        public Meta(HttpStatus status) {
            this(status.value(), status.getReasonPhrase());
        }

        public Meta(Integer status, String msg) {
            this.msg = msg;
            this.status = status;
        }
    }
}
