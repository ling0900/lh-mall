package com.lh.mall.portal.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//scanBasePackages = {"com.lh.mall.ums"}因为导入了其他依赖包，所以这里需要手动弄进来。这个引得包需要注意粒度。
@SpringBootApplication(scanBasePackages = {"com.lh.mall"})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
