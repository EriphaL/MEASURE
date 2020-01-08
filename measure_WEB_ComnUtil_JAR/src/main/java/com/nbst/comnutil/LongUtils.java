package com.nbst.comnutil;


public class LongUtils {
    public static Long changeIntegerToLong(Integer integer) {
        return Long.valueOf(integer);
    }

    public static Integer changeLongToInteger(Long l) {
        long ll = l.longValue();
        int ii = (int) ll;
        return ii;
    }
}
