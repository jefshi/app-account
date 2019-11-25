package com.csp.utillib;

import java.util.Collection;

/**
 * Description: 判定数据是否为空
 * <p>Create Date: 2017/07/18
 * <p>Modify Date: 2018/05/14
 *
 * @author csp
 * @version 1.0.2
 * @since JavaLibrary 1.0.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class EmptyUtil {
    /**
     * 字符串是否为空
     *
     * @param chars 字符串
     * @return true: 是
     */
    public static boolean isEmpty(CharSequence chars) {
        return chars == null || chars.length() == 0;
    }

    /**
     * 字符串内容是否为空（会去掉左右空白字符后再判断）
     *
     * @param chars 字符串
     * @return true: 是
     */
    public static boolean isBlank(CharSequence chars) {
        return chars == null || chars.toString().trim().isEmpty();
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return true: 是
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 数组是否为空
     *
     * @param array 集合
     * @return true: 是
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @see #isEmpty(Object[])
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @see #isEmpty(Object[])
     */
    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @see #isEmpty(Object[])
     */
    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @see #isEmpty(Object[])
     */
    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @see #isEmpty(Object[])
     */
    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }
}
