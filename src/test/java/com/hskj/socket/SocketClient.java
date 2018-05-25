package com.hskj.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by hongHan_gao
 * Date: 2018/5/23
 * 客户端
 */


public class SocketClient {

    public static void main(String[] args) throws IOException {
        String host = "172.16.30.10";
        int port = 5546;
        //与服务器建立连接
        Socket socket = new Socket(host, port);
        //建立连接后。获取输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "hello";
        outputStream.write(message.getBytes());
        //通过shutdownOutput关闭输出流来告诉服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while((length = inputStream.read(bytes)) != -1){
            stringBuilder.append(new String(bytes, 0, length, "UTF-8"));
        }
        System.out.println("get message from server:" + stringBuilder.toString());
        inputStream.close();
        outputStream.close();
        socket.close();
    }

}
