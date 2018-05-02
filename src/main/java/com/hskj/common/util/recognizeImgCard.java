package com.hskj.common.util;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 图片识别
 * 身份证识别url: https://api-cn.faceplusplus.com/cardpp/v1/ocridcard
 * 银行卡识别url: https://api-cn.faceplusplus.com/cardpp/beta/ocrbankcard
 */
public class recognizeImgCard {

    //图片: 二进制文件
    private final static int IMAGEFILE = 0;
    //base64编码后的二进制文件
    private final static int IMAGEBASE64 = 1;
    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();

    public static void main(String[] args) {
        boolean result = recognizeCardImg(
                "https://api-cn.faceplusplus.com/cardpp/beta/ocrbankcard",
                "9sHZ6y5TkUXRNfqc1fB6vFCy6F4zJ6Vw",
                "UtllHi56yK6Y8Be5faIZ_som9iaUtGhb",
                "E:/images/bankcard.jpg",
                1);
        System.out.println("【结果】:" + result);
    }

    /**
     * 识别证件图片
     * @param apiUrl    请求地址
     * @param apiKey    face++申请的api_key
     * @param apiSecret face++申请的api_secret
     * @param imgUrl    证件图片地址
     * @param isBase64  证件图片是否进行base64编码
     * @return
     */
    public static boolean recognizeCardImg(String apiUrl, String apiKey, String apiSecret, String imgUrl, int isBase64){
        Map<String, String> paramMap = new HashMap<String, String>();
        Map<String, byte[]> byteMap = new HashMap<>();
        boolean flag = false;
        try {
            paramMap.put("api_key", apiKey);
            paramMap.put("api_secret", apiSecret);
            if(isBase64 == IMAGEBASE64){
                String imgBase64 = imageToBase64(imgUrl);
                paramMap.put("image_base64", imgBase64);
            }else{
                byte[] imgByte = getBytesFromFile(new File(imgUrl));
                byteMap.put("image_file", imgByte);
            }
            byte [] result = post(apiUrl, paramMap, byteMap);
            JSONObject jsonObject = JSONObject.parseObject(new String(result));
            System.out.println(jsonObject);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * post方式发送数据到face++服务器
     * @param url
     * @param map
     * @param fileMap
     * @return
     * @throws Exception
     */
    protected static byte[] post(String url, Map<String, String> map, Map<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if (fileMap != null && fileMap.size() > 0) {
            Iterator fileIter = fileMap.entrySet().iterator();
            while (fileIter.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try {
            if (code == 200) {
                ins = conne.getInputStream();
            } else {
                ins = conne.getErrorStream();
            }
        } catch (SSLException e) {
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while ((len = ins.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }

    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    /**
     * 将图片转化成字节数组
     * @param f
     * @return
     */
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 对图片进行base64加密
     * @param path
     * @return 返回Base64编码过的字节数组字符串
     */
    private static String imageToBase64(String path){
        byte [] data = null;
        //读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            //in.available(): 获取六种可读取数据的大小
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //对字节数组进行base64加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
