package com.hskj.service;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.Assert;

import java.io.*;

/**
 * Created by hongHan_gao
 * Date: 2018/5/3
 */


public class WXPayConfigImpl implements WXPayConfig {

    private byte[] certData;
    private static WXPayConfigImpl instance;

    private WXPayConfigImpl(){
        String certPath = "/cert/apiclient_cert.p12";
        File file = new File(certPath);
        try {
            InputStream certStream = new FileInputStream(file);
            this.certData = new byte[(int) file.length()];
            certStream.read(this.certData);
            certStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static WXPayConfigImpl getInstance(){
        if(null == instance){
            synchronized (WXPayConfigImpl.class){
                if(null == instance){
                    instance = new WXPayConfigImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public String getAppID() {
        return "wx773e639c6f6fa63f";
    }

    @Override
    public String getMchID() {
        return "1503828351";
    }

    @Override
    public String getKey() {
        return "f25gf43d5g3d4fg23d87rg396d5g23d8";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
