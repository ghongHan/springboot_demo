package com.hskj.controller;

import com.aliyuncs.http.HttpResponse;
import com.google.common.base.Strings;
import com.hskj.common.dto.Message;
import com.hskj.common.dto.MessageType;
import com.hskj.common.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongHan_gao
 * Date: 2018/1/17
 */
@Api(value = "/user", tags = "用户模块")
@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController implements HttpSessionListener{

    //用户和session绑定关系
    private static final Map<String, HttpSession> USER_SESSION = new HashMap<>();

    //sessionId和用户绑定关系
    private static final Map<String, String> SESSIONID_USER = new HashMap<>();

    /**
     * 实现HttpSessionListener监听，监听session的创建事件
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        String sessionId = httpSessionEvent.getSession().getId();
        log.info("【创建session" + sessionId + "】");
    }

    /**
     * 实现httpSessionListener监听，监听session的销毁事件
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //当前session销毁时，删除SESSIONID_USER里当前的sessionId，同时删除USER_SESSION里的user
        String sessionId = httpSessionEvent.getSession().getId();
        USER_SESSION.remove(SESSIONID_USER.remove(sessionId));

    }

    /**
     * 用户登录实现同一时间同一账号只能一处登录，挤掉已登录
     * @param request
     * @param response
     * @param user
     * @return
     */
    @ApiIgnore
    @RequestMapping("/login.do")
    public ResponseEntity<Message> login(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //获取请求指令
        request.setCharacterEncoding("utf-8");
        //获取请求的路径
        String requestPath = request.getServletPath();
        String url = requestPath.substring(requestPath.lastIndexOf("/") + 1, requestPath.lastIndexOf(".do"));

        if("login".equals(url)){
            HttpSession session = request.getSession();
            String userName = user.getUserName();
            String password = user.getPassword();
            if(!Strings.isNullOrEmpty(userName)){
                if(login(userName, password)){
                    //处理登录（保持同一时间同一账号只能一处登录，挤掉前者）
                    userLoginHandle(userName, request);
                    //添加用户与HttpSession的绑定
                    USER_SESSION.put(userName.trim(), session);
                    //添加sessionId与用户的绑定
                    SESSIONID_USER.put(session.getId(), userName.trim());
                    log.info("用户【" + userName + "】上线");
                    return new ResponseEntity<Message>(new Message(MessageType.SUCCESS), HttpStatus.OK);
                }else{
                    log.info("用户【" + userName + "】登录失败,请重新登录");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return new ResponseEntity<Message>(new Message(MessageType.ERROR, "登录失败"), HttpStatus.OK);
                }
            }
        }else{
            return new ResponseEntity<Message>(new Message(MessageType.ERROR, "请求地址错误"), HttpStatus.OK);
        }
        return new ResponseEntity<Message>(new Message(MessageType.ERROR), HttpStatus.OK);
    }

    /**
     * 用户的登录处理
     * @param userName
     */
    private void userLoginHandle(String userName, HttpServletRequest request) {
        //删除当前sessionId已绑定的HttpSession
        HttpSession session = USER_SESSION.remove(userName);
        if(null != session){
            //删除已登录的sessionId绑定的用户
            SESSIONID_USER.remove(session.getId());
            //查看登录的浏览器
            String agent = request.getHeader("User-Agent").toLowerCase();
            log.info("帐号已经在【" + agent + "】登录，您被迫下线！");
        }
    }

    /**
     * 模拟登录
     * @param userName
     * @param password
     * @return
     */
    private boolean login(String userName, String password) {
        return ("gaohh".equals(userName) && "123456".equals(password));
    }

}
