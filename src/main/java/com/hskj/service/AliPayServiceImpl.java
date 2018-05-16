package com.hskj.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.hskj.common.dto.Message;
import com.hskj.common.dto.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hongHan_gao
 * Date: 2018/3/14
 */

@Service
public class AliPayServiceImpl implements AliPayService {

    @Override
    public ResponseEntity<Message> aliPay() {
        String privateKey = "XXXXXXX";
        String aLiPayPublicKey = "XXXXXXXXXX";
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
            if(response.isSuccess()){
                new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "统一下单成功", response.getBody()), HttpStatus.OK);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(new Message(MessageType.ERROR, "统一下单失败"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> aliPayNotify(HttpServletRequest request) {
        String alipaypublicKey = "XXXXXXXX";
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        try {
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean flag = AlipaySignature.rsaCheckV1(params, alipaypublicKey, "utf-8","RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(new Message(MessageType.SUCCESS), HttpStatus.OK);
    }
}
