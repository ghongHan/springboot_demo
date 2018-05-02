package com.hskj.controller;

import com.hskj.common.dto.CodeDto;
import com.hskj.common.dto.Message;
import com.hskj.common.util.Generator;
import com.hskj.service.RandomCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hongHan_gao
 * Date: 2018/1/5
 */
@Api(value = "/code", tags = "短信模块")
@RestController
@RequestMapping("/code")
public class RandomCodeController {

    @Autowired
    private RandomCodeService randomCodeService;

    /**
     * 获取短信验证码
     * @param mobile 手机号
     * @param content 短信模版
     * @param request
     * @return
     */

    @ApiOperation(value = "获取短信验证码")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "mobile", value = "手机号", required = true)
                    , @ApiImplicitParam(paramType = "query", dataType = "String", name = "content", value = "短信内容", required = true)})
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<Message> getMsgCode(@RequestParam("mobile") String mobile, @RequestParam("content") String content, HttpServletRequest request){
        String code = Generator.randomCode();
        request.getSession().setAttribute("msgCode", code);
        return randomCodeService.getMsgCode(mobile, code, content, request);
    }
}
