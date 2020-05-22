package com.csp.account.alipay;

import com.csp.account.alipay.dto.AlipayRecord;
import com.csp.account.alipay.dto.FilterRecord;
import com.csp.utillib.GsonUtil;
import com.csp.utillib.LogCat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付宝——所有交易记录——下载查询结果（csv）—解析
 * Created by csp on 2019/11/25
 * Modified by csp on 2019/11/25
 *
 * @version 1.0.0
 */
public class AlipayReader {

    public static void main(String[] argv) {
        File file = new File("D:/D_User/Downloads/alipay_record/alipay_record_20200522_1128_1.csv");
        List<AlipayRecord> records = readRecord(file);

        String log = GsonUtil.toJson(records);
        System.err.println(log);
    }

    /**
     * 获取过滤后的交易记录
     *
     * @param file 文件来源于：支付宝——所有交易记录——下载查询结果（csv）
     * @return 交易记录，只含日常，不记录理财、借贷
     */
    public static List<AlipayRecord> readRecord(File file) {
        return parseFile(file).stream()
                .filter(FilterRecord::filterDaily)
                .collect(Collectors.toList());
    }

    /**
     * 所有记录
     *
     * @param file 文件来源于：支付宝——所有交易记录——下载查询结果（csv）
     * @return 所有记录
     */
    public static List<AlipayRecord> parseFile(File file) {
        List<AlipayRecord> list = new ArrayList<>();
        if (file == null || !file.isFile()) {
            return list;
        }

        BufferedReader reader = null;
        try {
            String encoding = "GBK";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));

            String line;
            while ((line = reader.readLine()) != null) {
                AlipayRecord record = new AlipayRecord(line);
                record.parse();
                list.add(record);
            }
            return list;
        } catch (IOException e) {
            LogCat.printStackTrace(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LogCat.printStackTrace(e);
                }
            }
        }
        return list;
    }
}
