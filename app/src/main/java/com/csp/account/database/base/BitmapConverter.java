package com.csp.account.database.base;

import android.graphics.Bitmap;

import com.csp.utillib.classutil.BitmapUtil;
import com.csp.utillib.classutil.ByteUtil;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author chensp01
 * date 2019/8/14 14:30
 * @Description: 消息存储
 */
public class BitmapConverter implements PropertyConverter<Bitmap, byte[]> {

    @Override
    public Bitmap convertToEntityProperty(byte[] database) {
        return BitmapUtil.toBitmap(database);
    }

    @Override
    public byte[] convertToDatabaseValue(Bitmap entity) {
        return ByteUtil.toBytes(entity, 100);
    }
}