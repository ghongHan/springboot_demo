package com.hskj.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * Created by hongHan_gao
 * Date: 2018/5/2
 * http请求工具类
 */


public class HttpUtil {

    //获取终端ip
    public static String getRemoteIP(HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
            //根据网卡获取本机配置的IP地址
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            ipAddress = inetAddress.getHostAddress();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if(null != ipAddress && ipAddress.length() > 15){
            if(ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
