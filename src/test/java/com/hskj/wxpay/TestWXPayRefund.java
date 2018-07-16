package com.hskj.wxpay;

import com.github.wxpay.sdk.WXPay;
import com.hskj.service.WXPayConfigImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongHan_gao
 * Date: 2018/5/28
 * 测试微信退款
 */


public class TestWXPayRefund {

    public static void main(String[] args) {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxpay = new WXPay(config);
        Map<String, String> param = new HashMap<>();
        param.put("out_trade_no", "HHDC20180528174303P354");
        param.put("out_refund_no", "HHDC20180528174303P354");
        param.put("total_fee", "10000");
        param.put("refund_fee", "10000");
        param.put("refund_fee_type", "CNY");
        param.put("notify_url", "http://112.17.127.21:35050/wxpay_notify/notify");
        try {
            System.out.println(wxpay.refund(param));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
