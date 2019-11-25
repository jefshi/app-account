package com.csp.utillib;

import com.google.gson.Gson;

public class GsonUtil {

    private volatile static Gson gson = null;

    private GsonUtil() {
    }

    public static Gson getInstance() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return getInstance().fromJson(json, classOfT);
        } catch (Throwable t) {
            LogCat.printStackTrace(t);
            return null;
        }
    }

    public static String toJson(Object src) {
        try {
            return getInstance().toJson(src);
        } catch (Throwable t) {
            LogCat.printStackTrace(t);
            return null;
        }
    }
}
