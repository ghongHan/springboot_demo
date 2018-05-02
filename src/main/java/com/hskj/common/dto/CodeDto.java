package com.hskj.common.dto;

import lombok.Data;

/**
 * 验证码dto
 * Created by hongHan_gao
 * Date: 2018/1/5
 */

@Data
public class CodeDto {

    //手机号
    private String phoneNum;
    //短信模版
    private String smsTemplate;

}
