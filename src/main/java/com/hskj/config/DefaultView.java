package com.hskj.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hongHan_gao
 * Date: 2017/12/20
 */

@Configuration
public class DefaultView extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

    /**
     * 设置项目启动时的默认页
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        super.addViewControllers(registry);
    }

    /**
     * 捕获404，500异常
     * @param container
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(new ErrorPage[]{
                new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html")
        });
    }
}
