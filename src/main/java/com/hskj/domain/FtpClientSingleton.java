package com.hskj.domain;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * FtpClient单例对象
 * Created by hongHan_gao
 * Date: 2018/1/12
 */


public class FtpClientSingleton {

    private static volatile FtpClientSingleton instance = null;

    private String name;

    private FtpClientSingleton(){}

    public static FtpClientSingleton getInstance(){
        if(null == instance){
            synchronized (FtpClientSingleton.class){
                if(null == instance){
                    instance = new FtpClientSingleton();
                }
            }
        }
        return instance;
    }

    /**
     * 连接ftp
     * @param host       FTP服务器hostname
     * @param port       FTP服务器度端口
     * @param userName   FTP登录账号
     * @param password   FTP登录密码
     * @return
     */
    public static FTPClient ftpConnect(String host, int port, String userName, String password){
        FTPClient client = new FTPClient();
        try {
            client.connect(host, port);
            client.setControlEncoding("UTF-8");
            client.enterLocalPassiveMode();
            client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            client.login(userName, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public String getName() {
        return name;
    }

    public FtpClientSingleton setName(String name) {
        this.name = name;
        return this;
    }
}
