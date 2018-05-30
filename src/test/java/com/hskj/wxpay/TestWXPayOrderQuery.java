package com.hskj.wxpay;

import com.github.wxpay.sdk.WXPay;
import com.hskj.service.WXPayConfigImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongHan_gao
 * Date: 2018/5/29
 * 测试微信订单查询
 */


public class TestWXPayOrderQuery {

    public static void main(String[] args) {
        try {
            WXPayConfigImpl config = WXPayConfigImpl.getInstance();
            WXPay wxpay = new WXPay(config);
            Map<String, String> param = new HashMap<>();
            param.put("out_trade_no","HHDC20180528174303P354");
            System.out.println(wxpay.orderQuery(param));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
