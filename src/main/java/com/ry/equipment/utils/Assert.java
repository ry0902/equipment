package com.ry.equipment.utils;

import com.ry.equipment.exception.NullArgsException;

public final class Assert {

    public static void assertNotNull(Object ...args) {
        for (Object o: args) {
            if (o == null) {
                throw new NullArgsException("参数为空");
            }
        }
    }

    public static void assertTrue(boolean f) {
        if (!f) {
            throw new NullArgsException("参数为空");
        }
    }
}
