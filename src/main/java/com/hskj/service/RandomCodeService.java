package com.hskj.service;

import com.hskj.common.dto.Message;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hongHan_gao
 * Date: 2018/1/5
 */

public interface RandomCodeService {

    public ResponseEntity<Message> getMsgCode(String phoneNum, String code, String smsTemplate, HttpServletRequest request);

}
