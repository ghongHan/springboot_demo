package com.hskj.common.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

/**
 * Created by hongHan_gao
 * Date: 2018/3/14
 */


public class testAliPay {

    public static void main(String[] args) {
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFk2aBqxnN/l50FeczB5Ne1lWx1giv//BEayQ4D4egosd0pGqe2dyJZBULHWCQ556oOE9OgwqfhYiSuVK0Bn+TqskCRtAhNJjRxgbeWVtyDbrzQXxUzaHhamJxnzQxoiLRmCFauu9+ZqIEdhgEpAmO7/77fm0B2/Tn7wsVmFB345VghXdXcnyQ/e7ksz4CGTdcbKx1UHcN+KIMK09xwScrHJiEVuPvponIdv8N+s3BhAOZyUFXHWOV+6mc0gnFpWUP9rkF5pxFB0QN3adb5znwMpoNv9XwcyTHDiJPSACMZMcEgLvvw7bumLjv41d2AOPYmpGBLKtyJuWysz141uSfAgMBAAECggEANSMhGR5OwqKZQobgFW3DE2wiZP9nL1SaaS1SGDcdVirLO7yYaZ7zW3iD4osKOjrZ48Oa0PnSRQli/jNZEiKvp7e+BPjUnREGZhTn8JYNib6qi1CWvEmdR0YB6X9SzRfZ0BU715Mb6OW9OBcekOIMp18TyUpO5PlyYL24ZBvWjwpWfYC4P7bcgO1vP9T2C8+awXSdmWfVJd0ZSy+qQ0ZbJPcrOFqHEMMWTIdCzwjm/NhRp9wZqJd9B+hlnh5EHicyqd7po1NNrGkDHYIr5bv+FbcUSUanFHdgIDbtRhW4FIYpFQw4Y5FWb/RB+P71m3CJjdJ/yEqkNNAU+URegAW8SQKBgQDUXdOye1N02gqMsi9q00mvQd/3dzXdFdhci+ynPffDwsqvFP2zTAzHm+PkWXb9jXieFxEog9PQW4ob37oeHN3k7QU3NfGxzZtRGVsuQqBaPkqnASHS/6j30Tp8OJsNThu8YukCdC32jE4WD9hJ3hyZ3u7qqCgMDotLK7BDAfUpewKBgQChBUtimnBF8R7/1ZovTOGD3vjYyy+3p/Vjjh6w0H9MGmF7xvqJil+MgWD2pUcywzKZqM0JWmPniwbU+Q+wivHug9hEMZJC0FvIAA3sXm9/iXpsqwsFtV5S8gu45HqsbICpgY8b01VRI0YmRmuXRrQ7scXVgF/tgd5CZ7vR5ZeuLQKBgBPpjE0C9UtSmoSgvIN2tkUBlz975QgK4VOkJxdzXwTzAdAy1qr2KYiZ3F9v6BFUL4gbP5ed7MFvo96DNs9WA5RQiRmpLDVwwuxyxTLsOl7b0mIgYAyJvk0mSCaXpCo2NbGPUKvTOvAQLc4xxCUA94uR9LL5+29N+c+dlBRkXZl7AoGAWlbfGZ4uOX9zdfPAgqMur07M7Mzqo66vBUD6RRdlJCx03v4fGEWzH+CCpz7JeFh9rio/huzYulKM7bYisFj0LW55SqFJPwUpSUeGiEPfaH2YWxmHoJdGKWE1ZHGDxfNGKFPRF7p01oXk2JqPyJrZidhOd1+D8nv3V+hdPwECHa0CgYBw5TEQ5s3Lu31s1SLKj0CpBSRv/AmTbuFM85GgZrX2ggPFJRPgdoTvXO8fctrhmzkovMo0HVlqtx9Ih+e/9vFfPgARBHwGxl1b1upwp5pO1sfFCVI1K4YvTXNfNzg2RDjFnSyvojfVj8bCk5u4/ze4tP9kwUEGfBqoVmOy1dAGIw==";
        String aLiPayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhZNmgasZzf5edBXnMweTXtZVsdYIr//wRGskOA+HoKLHdKRqntnciWQVCx1gkOeeqDhPToMKn4WIkrlStAZ/k6rJAkbQITSY0cYG3llbcg2680F8VM2h4WpicZ80MaIi0ZghWrrvfmaiBHYYBKQJju/++35tAdv05+8LFZhQd+OVYIV3V3J8kP3u5LM+Ahk3XGysdVB3DfiiDCtPccEnKxyYhFbj76aJyHb/DfrNwYQDmclBVx1jlfupnNIJxaVlD/a5BeacRQdEDd2nW+c58DKaDb/V8HMkxw4iT0gAjGTHBIC778O27pi47+NXdgDj2JqRgSyrciblsrM9eNbknwIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2018031302367035", privateKey, "json", "utf-8",aLiPayPublicKey, "RSA2");
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("测试数据");
        model.setSubject("App支付测试");
        model.setOutTradeNo("201804202000025");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("https://d1ce3ba2.ngrok.io/pay/payNotify");
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", response.isSuccess());
            System.out.println(jsonObject.toJSONString());
            System.out.println("返回的：" + response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

}
