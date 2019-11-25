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

public class AlipayReader {

    public static void main(String[] argv) {
        File file = new File("D:/D_User/Downloads/alipay_record/alipay_record_20191106_1203_1.csv");
        List<AlipayRecord> records = readFile(file);

        FilterRecord.set交易对方为自己("");

        // 只含日常，不记录理财、借贷
        List<AlipayRecord> dailyRecords = records.stream()
                .filter(FilterRecord::filterDaily)
                .collect(Collectors.toList());

        String log = GsonUtil.toJson(dailyRecords);
        System.err.println(log);
        // LogCat.e(GsonUtil.toJson(dailyRecords));
    }

    /**
     * @param file 文件来源于：支付宝——交易记录——下载查询结果
     */
    public static List<AlipayRecord> readFile(File file) {
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
