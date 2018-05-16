package com.hskj.controller;

import com.hskj.common.dto.Message;
import com.hskj.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hongHan_gao
 * Date: 2018/3/14
 * 支付的最全参考文档：https://gitee.com/52itstyle/spring-boot-pay
 * 支付宝统一下单接口见com.hskj.common.testAliPay
 */
@Api(tags = "支付模块")
@RestController
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    @ApiOperation(value="支付宝统一下单")
    @RequestMapping(value = "/aliPay", method = RequestMethod.GET)
    public ResponseEntity<Message> aliPay(){
        return aliPayService.aliPay();
    }

    @ApiOperation(value="支付宝支付通知")
    @RequestMapping(value = "/payNotify", method = RequestMethod.GET)
    public ResponseEntity<Message> payNotify(HttpServletRequest request){
        return  aliPayService.aliPayNotify(request);
    }

}
