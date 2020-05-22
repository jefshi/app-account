package com.csp.account.alipay.dto;

/**
 * 支付宝——所有交易记录——下载查询结果（csv）
 * Created by csp on 2019/11/25
 * Modified by csp on 2019/11/25
 *
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "NonAsciiCharacters", "unused"})
public class AlipayRecord {

    private String allRaw; // 原始数据
    private long tradeDate; // 交易时间，来源于[交易创建时间]

    private String 交易号;
    private String 商家订单号;
    private String 交易创建时间;
    private String 付款时间;
    private String 最近修改时间;
    private String 交易来源地;
    private String 类型;
    private String 交易对方;
    private String 商品名称;
    private String 金额; // （元）
    private String 收或支;
    private String 交易状态;
    private String 服务费; // （元）
    private String 成功退款; // （元）
    private String 备注;
    private String 资金状态;

    public AlipayRecord(String allRaw) {
        this.allRaw = allRaw;
    }

    public void parse() {
        ParseRecord.parse(this);
    }

    public String getAllRaw() {
        return allRaw;
    }

    public long getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(long tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String get交易号() {
        return 交易号;
    }

    public void set交易号(String 交易号) {
        this.交易号 = 交易号;
    }

    public String get商家订单号() {
        return 商家订单号;
    }

    public void set商家订单号(String 商家订单号) {
        this.商家订单号 = 商家订单号;
    }

    public String get交易创建时间() {
        return 交易创建时间;
    }

    public void set交易创建时间(String 交易创建时间) {
        this.交易创建时间 = 交易创建时间;
    }

    public String get付款时间() {
        return 付款时间;
    }

    public void set付款时间(String 付款时间) {
        this.付款时间 = 付款时间;
    }

    public String get最近修改时间() {
        return 最近修改时间;
    }

    public void set最近修改时间(String 最近修改时间) {
        this.最近修改时间 = 最近修改时间;
    }

    public String get交易来源地() {
        return 交易来源地;
    }

    public void set交易来源地(String 交易来源地) {
        this.交易来源地 = 交易来源地;
    }

    public String get类型() {
        return 类型;
    }

    public void set类型(String 类型) {
        this.类型 = 类型;
    }

    public String get交易对方() {
        return 交易对方;
    }

    public void set交易对方(String 交易对方) {
        this.交易对方 = 交易对方;
    }

    public String get商品名称() {
        return 商品名称;
    }

    public void set商品名称(String 商品名称) {
        this.商品名称 = 商品名称;
    }

    public String get金额() {
        return 金额;
    }

    public void set金额(String 金额) {
        this.金额 = 金额;
    }

    public String get收或支() {
        return 收或支;
    }

    public void set收或支(String 收或支) {
        this.收或支 = 收或支;
    }

    public String get交易状态() {
        return 交易状态;
    }

    public void set交易状态(String 交易状态) {
        this.交易状态 = 交易状态;
    }

    public String get服务费() {
        return 服务费;
    }

    public void set服务费(String 服务费) {
        this.服务费 = 服务费;
    }

    public String get成功退款() {
        return 成功退款;
    }

    public void set成功退款(String 成功退款) {
        this.成功退款 = 成功退款;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }

    public String get资金状态() {
        return 资金状态;
    }

    public void set资金状态(String 资金状态) {
        this.资金状态 = 资金状态;
    }
}
