package com.hskj.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.hskj.common.dto.Message;
import com.hskj.common.dto.MessageType;
import com.hskj.common.util.HttpUtil;
import com.hskj.service.WXPayConfigImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by hongHan_gao
 * Date: 2018/5/2
 * 微信支付
 */
@Api(tags = "支付模块")
@RestController
@RequestMapping("/wxPay")
public class WxPayController {

    private WXPay wxPay;
    private WXPayConfigImpl config;

    public WxPayController(){
        config = WXPayConfigImpl.getInstance();
        wxPay = new WXPay(config);
    }

    @ApiOperation(value="微信的统一下单")
    @RequestMapping("/unifiedOrder")
    public ResponseEntity<Message> unifiedOrder(HttpServletRequest request){
        //用于app调起支付的参数定义
        String prepayId = null;
        String sign = null;
        String nonce_str = null;

        //统一下单参数
        String remoteIP = HttpUtil.getRemoteIP(request);
        Map<String, String> param = new HashMap<String, String>();
        param.put("body","恒胜科技网约车平台");
        param.put("out_trade_no","20180502163256");
        param.put("total_fee","1");
        param.put("spbill_create_ip", remoteIP);
        param.put("notify_url", "http://app.lvshuicar.com/autocar_wechat/wxPay/notify");
        param.put("trade_type", "APP");
        try {
            //微信统一下单时会进行第一次签名
            Map<String, String> result = wxPay.unifiedOrder(param);
            //进行签名校验
            if(wxPay.isResponseSignatureValid(result)){
                Set<String> keys = result.keySet();
                for(String key : keys){
                    switch (key){
                        case "prepay_id":
                            prepayId = result.get(key);
                            continue;
                        case "nonce_str":
                            nonce_str = result.get(key);
                            continue;
                    }
                }
                //返回的参数
                Map<String, Object> resParam = new HashMap<>();
                resParam.put("appid", config.getAppID());
                resParam.put("partnerid", config.getMchID());
                resParam.put("prepayid", prepayId);
                resParam.put("package", "Sign=WXPay");
                resParam.put("noncestr", nonce_str);
                resParam.put("timestamp", System.currentTimeMillis()/1000);

                //二次签名
                Map<String, String> secondParams = new TreeMap<>();
                secondParams.put("appid", config.getAppID());
                secondParams.put("partnerid", config.getMchID());
                secondParams.put("prepayid", prepayId);
                secondParams.put("noncestr", nonce_str);
                secondParams.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
                secondParams.put("package", "Sign=WXPay");
                String secondSign = WXPayUtil.generateSignature(secondParams, config.getKey(), WXPayConstants.SignType.MD5);

                resParam.put("sign", secondSign);

                return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, resParam), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Message>(new Message(MessageType.ERROR, "统一下单失败"), HttpStatus.OK);
    }

    /**
     * 微信支付回调通知
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ApiOperation(value="微信支付回调通知")
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resultMSg = null;
        //返回给微信服务器
        String resultXml = null;
        try {
            InputStream inputStream = request.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            String result = new String(outputStream.toByteArray(), "UTF-8");
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            if ("SUCCESS".equals(resultMap.get("result_code"))) {
                resultXml = "<xml>"
                        + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>"
                        + "</xml> ";
                resultMSg =  "SUCCESS";
            } else {
                resultXml = "<xml>"
                        + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[签名验证错误]]></return_msg>"
                        + "</xml> ";
                resultMSg =  "FAIL";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resultXml.getBytes());
        out.flush();
        out.close();
        return resultMSg;
    }

    @ApiOperation(value="微信申请退款")
    @RequestMapping("/refund")
    public String refund(){
        Map<String, String> param = new HashMap<>();
        param.put("out_trade_no", "HHDC20180528174303P354");
        param.put("out_refund_no", "HHDC20180528174303P354");
        param.put("total_fee", "10000");
        param.put("refund_fee", "10000");
        param.put("refund_fee_type", "CNY");
        Map<String, String> resultMap = null;
        String result = null;
        try {
            resultMap = wxPay.refund(param);
            if("SUCCESS".equals(resultMap.get("result_code"))){
                result = "退款成功";
            }else{
                result = "退款失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value="微信订单查询")
    @RequestMapping("/orderQuery")
    public void orderQuery(){
        try {
            Map<String, String> param = new HashMap<>();
            param.put("out_trade_no", "HHDC20180528174303P354");
            param.put("out_refund_no", "HHDC20180528174303P354");
            param.put("total_fee", "10000");
            param.put("refund_fee", "10000");
            param.put("refund_fee_type", "CNY");
            Map<String, String> result = wxPay.orderQuery(param);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
