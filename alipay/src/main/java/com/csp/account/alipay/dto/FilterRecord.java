package com.csp.account.alipay.dto;

/**
 * 支付宝——所有交易记录——下载查询结果（csv）—解析
 * Created by csp on 2019/11/25
 * Modified by csp on 2019/11/25
 *
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "NonAsciiCharacters"})
public class FilterRecord {

    public final static String[] 交易状态为无效 = {
            "交易关闭",
            "已关闭",
            "转账失败",
            "还款失败",
    };

    /**
     * 即自身账户之间的转账记录关键字
     */
    public final static String[] 交易对方为自身账户 = {
            "花呗",
            "蚂蚁花呗",
            "招商银行",
            "中国工商银行",
            "中国建设银行",
            "中国银行",
            "中信银行",
    };

    public final static String[] 交易对方为借贷 = {
            "借呗",
            "蚂蚁借呗",
    };

    public final static String[] 交易对方为理财 = {
            "建信养老金管理有限责任公司",
            "博时黄金-蚂蚁（杭州）基金销售有限公司",
            "博时基金管理有限公司",
            "博时基金管理有限公司（GOLD）",
            "存金宝-蚂蚁（杭州）基金销售有限公司",
            "存金宝-数米基金销售有限公司",
            "蚂蚁财富-蚂蚁（杭州）基金销售有限公司",
            "蚂蚁聚宝-蚂蚁（杭州）基金销售有限公司",
            "蚂蚁聚宝-数米基金销售有限公司",
    };


    public final static String[] 交易对方为余额宝 = {
            "景顺长城基金管理有限公司", // 余额宝
            "天弘基金管理有限公司", // 余额宝
    };

    public final static String 余额宝收益发放 = "收益发放"; // 余额宝收益发放关键字

    /**
     * 即自身账户之间的转账记录关键字
     */
    public final static String[] 商品名称为自身账户要求交易对方为自己 = {
            "余额宝-转出到余额",
            "转账到银行卡-转账",
    };

    /**
     * 理财相关
     */
    public final static String 商品名称含招财宝 = "招财宝";

    /**
     * 支付宝持有人
     */
    public static String 交易对方为自己;

    public static void set交易对方为自己(String 交易对方为自己) {
        FilterRecord.交易对方为自己 = 交易对方为自己;
    }

    /**
     * 只含日常，不记录理财、借贷
     *
     * @return false：记录将被过滤
     */
    public static boolean filterDaily(AlipayRecord record) {
        // 过滤：无效数据
        if (record.getTradeDate() == 0)
            return false;

        String 交易状态 = record.get交易状态();
        if (交易状态 == null)
            交易状态 = "";

        String 交易对方 = record.get交易对方();
        if (交易对方 == null)
            交易对方 = "";

        String 商品名称 = record.get商品名称();
        if (商品名称 == null)
            商品名称 = "";

        // 过滤：交易状态为无效的数据
        for (String datum : 交易状态为无效) {
            if (交易状态.contains(datum))
                return false;
        }

        // 过滤：自身账户间的转账（含花呗）
        for (String datum : 交易对方为自身账户) {
            if (交易对方.contains(datum))
                return false;
        }
        for (String datum : 商品名称为自身账户要求交易对方为自己) {
            if (商品名称.contains(datum) && 交易对方.equals(交易对方为自己))
                return false;
        }

        // 过滤：借贷相关记录
        for (String datum : 交易对方为借贷) {
            if (交易对方.contains(datum))
                return false;
        }

        // 过滤：理财相关记录
        for (String datum : 交易对方为理财) {
            if (交易对方.contains(datum))
                return false;
        }
        if (商品名称.contains(商品名称含招财宝))
            return false;

        // 处理余额宝相关记录：过滤掉余额宝与自身账户之前的转账、保留余额宝收入
        for (String datum : 交易对方为余额宝) {
            if (交易对方.contains(datum))
                return !商品名称.contains(余额宝收益发放);
        }
        return true;
    }
}
