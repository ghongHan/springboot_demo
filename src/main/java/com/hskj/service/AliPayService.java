package com.hskj.service;

import com.hskj.common.dto.Message;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hongHan_gao
 * Date: 2018/3/14
 */


public interface AliPayService {

    public ResponseEntity<Message> aliPay();

    public ResponseEntity<Message> aliPayNotify(HttpServletRequest request);
}
