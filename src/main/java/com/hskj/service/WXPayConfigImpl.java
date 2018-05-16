package com.hskj.service;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.Assert;

import java.io.InputStream;

/**
 * Created by hongHan_gao
 * Date: 2018/5/3
 */


public class WXPayConfigImpl implements WXPayConfig {

    private static WXPayConfigImpl instance;

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
        return "XXXXXXXX";
    }

    @Override
    public String getMchID() {
        return "XXXXXXXX";
    }

    @Override
    public String getKey() {
        return "XXXXXXXX";
    }

    @Override
    public InputStream getCertStream() {
        return null;
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
