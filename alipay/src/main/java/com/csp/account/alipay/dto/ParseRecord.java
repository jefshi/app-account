package com.csp.account.alipay.dto;

import com.csp.utillib.EmptyUtil;
import com.csp.utillib.LogCat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解析记录
 * Created by csp on 2019/11/25
 * Modified by csp on 2019/11/25
 *
 * @version 1.0.0
 */
class ParseRecord {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    static void parse(AlipayRecord record) {
        String allRaw = record.getAllRaw();

        // 过滤掉前后无用的记录
        if (EmptyUtil.isBlank(allRaw) || !allRaw.startsWith("2"))
            return;

        String[] split = allRaw.split(",");
        record.set交易号(split[0].replace("\t", "").replace("\"", "").trim());
        record.set商家订单号(split[1].replace("\t", "").replace("\"", "").trim());
        record.set交易创建时间(split[2].trim());
        record.set付款时间(split[3].trim());
        record.set最近修改时间(split[4].trim());
        record.set交易来源地(split[5].trim());
        record.set类型(split[6].trim());
        record.set交易对方(split[7].trim());
        record.set商品名称(split[8].trim());
        record.set金额(split[9].trim());
        record.set收或支(split[10].trim());
        record.set交易状态(split[11].trim());
        record.set服务费(split[12].trim());
        record.set成功退款(split[13].trim());
        record.set备注(split[14].trim());
        record.set资金状态(split[15].trim());

        Date date = getDate(record.get交易创建时间());
        if (date != null)
            record.setTradeDate(date.getTime());
    }

    /**
     * 本地时间(字符串) -> 本地时间(Date)
     *
     * @param dateStr 本地时间(字符串)
     */
    private static Date getDate(String dateStr) {
        // 根据时间字符串，获取相应时间
        Date date;
        try {
            date = SDF.parse(dateStr);
        } catch (ParseException e) {
            LogCat.printStackTrace(e);
            return null;
        }
        return date;
    }
}
