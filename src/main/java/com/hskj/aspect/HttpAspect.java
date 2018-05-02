package com.hskj.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面类（可统一管理日志的输出）
 * Created by hongHan_gao
 * Date: 2017/12/20
 */

@Aspect
@Component
@Slf4j
public class HttpAspect {

    /**
     * 定义全局切点
     */
    @Pointcut("execution(public * com.hskj.controller.TrainController.getAllTrains(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuilder stringBuilder = new StringBuilder();
        //获取请求的url
        log.info("ulr = {}", request.getRequestURI());
        //获取请求的method
        log.info("method = {}", request.getMethod());
        //获取请求的参数
        //获取请求的参数
        for (int i = 0; i < joinPoint.getArgs().length - 1; i++) {
            stringBuilder.append(joinPoint.getArgs()[i]);
            if (i != joinPoint.getArgs().length - 2) {
                stringBuilder.append(",");
            }
        }
        log.info("params = {}", stringBuilder);

        log.info("请求getAllTrains（）方法之前，记录日志");
    }

    @After("log()")
    public void doAfter(){
        log.info("请求getAllTrains（）方法之后，记录日志");
    }

    /**
     * 获取返回内容
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        log.info("response = {}", object.toString());
    }


}
