package com.ry.equipment.exception;

public class NullArgsException extends ArgsException {
    public NullArgsException() {
        this("参数为空");
    }

    public NullArgsException(String message) {
        super(message);
    }
}
