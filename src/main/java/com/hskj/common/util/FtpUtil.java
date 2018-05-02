package com.hskj.common.util;

import com.hskj.domain.FtpClientSingleton;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * ftp上传下载工具类
 * Created by hongHan_gao
 * Date: 2018/1/9
 */


public class FtpUtil {


    /**
     * 上传文件（使用了单例模式）
     * @param host        FTP服务器hostname
     * @param port        FTP服务器度端口
     * @param userName    FTP登录账号
     * @param password    FTP登录密码
     * @param basePath    FTP服务器基础目录
     * @param filePath    FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param fileName    上传到FTP服务器上的文件名
     * @param inputStream 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String userName, String password,
                                     String basePath, String filePath, String fileName, InputStream inputStream) {

        boolean result = false;
        FTPClient ftp = FtpClientSingleton.getInstance().setName("aaa")
                            .ftpConnect(host, port, userName, password);

        int reply;
        try {
            //连接FTP服务器
 //           ftp.connect(host, port);
 //           ftp.setControlEncoding("UTF-8");
 //           ftp.enterLocalPassiveMode();
 //           ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
  //          ftp.login(userName, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            String tempPath = basePath + filePath;
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(tempPath)) {
                System.out.println(tempPath + "不存在");
                //如果目录不存在则创建目录
                if (!ftp.makeDirectory(tempPath)) {
                    System.out.println("创建目录" + tempPath + "失败");
                    return result;
                }
            }
            //回到保存目录
            ftp.changeWorkingDirectory(tempPath);
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftp.storeFile(new String(fileName.getBytes(), "iso-8859-1"), inputStream)) {
                System.out.println("上传文件失败");
                return result;
            }
            inputStream.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    inputStream.close();
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 下载文件（未使用单例模式）
     * @param url            FTP服务器hostname
     * @param port           FTP服务器端口
     * @param userName       FTP登录账号
     * @param password       FTP登录密码
     * @param remotePath     FTP服务器上的相对路径
     * @param fileName       要下载的文件名
     * @param localPath      下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String userName, String password,
              String remotePath, String fileName, String localPath){

        boolean result = false;
        FTPClient client = FtpClientSingleton.getInstance().setName("bbb")
                .ftpConnect(url, port, userName, password);
        try {
            int reply;
/*            client.connect(url, port);
            client.setControlEncoding("UTF-8");
            client.enterLocalPassiveMode();
            client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            client.login(userName, password);*/
            reply = client.getReplyCode();
            System.out.println("实例名：" + FtpClientSingleton.getInstance().getName());
            if(!FTPReply.isPositiveCompletion(reply)){
                client.disconnect();
                return result;
            }
            //转移到FTP服务器目录
            client.changeWorkingDirectory(remotePath);
            FTPFile[] files = client.listFiles();
            for (FTPFile file : files){
                if(file.getName().equals(fileName)){
                    File localFile = new File(localPath + "/" + file.getName());
                    OutputStream outputStream = new FileOutputStream(localFile);
                    client.retrieveFile(file.getName(), outputStream);
                    outputStream.close();
                }
            }
            client.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (client.isConnected()){
                try {
                    client.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        try {
            //上传文件
   /*         FileInputStream inputStream = new FileInputStream(new File("E:\\images\\idcard.jpg"));
            boolean result = uploadFile("172.16.30.43", 21, "ftpuser", "Abc12345", "/var", "/ftp/images", "004.jpg", inputStream);
            System.out.println(result);*/

            //下载文件
            String localPath = "E:/images";
            boolean result = downFile("172.16.30.43", 21, "ftpuser", "Abc12345", "/var/ftp/images", "004.jpg", localPath);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
