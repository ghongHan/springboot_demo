package com.hskj.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
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
    public ResponseEntity<Message> aliPayNotify(HttpServletRequest request) {
        String alipaypublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5XRpO9UMCC61qaAeSd9jPzloZ/oTGGTh5CojlbC1U5lNhFdhEDFFzLcvHKSzvOgeiMbpWjVsNCCi/tUem1k2t6JSGuVvrrwFlxVxE3v47I5SDt6tWjxZmTWtLHUhcN2CK6MB8S1WrABm55oCS4hJqRMi0tOjjxFda3DTwh2g7B9wxnbcatOEJs7XQkl7EaPHlyfACbS2ECTiIke5u2OZy/S5C/83lLElA8saw48M0elsAlAAosqyWM6lODPf/Oll6s43NYR2KdPcpQJK7My574cn4rVGgyLPDH1k4VePKpkzQ6JmgJ/A2qzjX0GqyVVYQel4axso1BZECEDnMobDfwIDAQAB";
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
