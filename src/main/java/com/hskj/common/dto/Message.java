package com.hskj.common.dto;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/18.
 */

@Data
public class Message<T> {

    private String status;

    private String error;

    private T data;

    public Message(String status){
        this.status = status;
    }

    public Message(String status, T data){
        this.status = status;
        this.data = data;
    }

    public Message(String status, String error){
        this.status = status;
        this.error = error;
    }

    public Message(String status, String error, T data){
        this.status = status;
        this.error = error == null ? "" : error;
        this.data = data;
    }

    public Message(){
        super();
    }

}
