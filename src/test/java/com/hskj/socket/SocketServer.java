package com.hskj.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hongHan_gao
 * Date: 2018/5/23
 * 服务器
 */


public class SocketServer {

    public static void main(String[] args) throws IOException {
        int port = 5546;
        ServerSocket server = new ServerSocket(port);
        //server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        //建立连接
        Socket socket = server.accept();
        //建立好连接后，从socket中获取输入流,并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bytes = new byte[1024];
        int length = 0;
        while((length = inputStream.read(bytes)) != -1){
            stringBuilder.append(new String(bytes, 0, length, "UTF-8"));
        }
        System.out.println("get message from client:" + stringBuilder.toString());
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(("server has get message:" + stringBuilder.toString()).getBytes());
        inputStream.close();
        outputStream.close();
        socket.close();
        server.close();
    }

}
