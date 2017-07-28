package com.example.jh.openeyes.utils;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class IDUtils {

    private static final int[] ids = new int[]{
            26, 28, 30, 32, 34, 36, 38,
            4, 8, 14, 22, 24, 2, 18, 6,
            12, 10, 20
    };

    public static boolean isDetermined(int categoryId) {
        for (int id : ids) {
            if (id == categoryId) {
                return true;
            }
        }
        return false;
    }

}

