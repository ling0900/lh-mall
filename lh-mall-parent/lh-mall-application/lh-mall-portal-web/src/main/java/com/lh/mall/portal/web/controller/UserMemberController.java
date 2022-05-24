package com.lh.mall.portal.web.controller;

import com.lh.mall.ums.entity.dto.UmsMemberLoginDTO;
import com.lh.mall.ums.entity.dto.UmsMemberRegisterDTO;
import com.lh.mall.ums.service.UmsMemberService;
import com.lhcommon.base.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 99261
 */
@RestController
@RequestMapping("/user-member")
public class UserMemberController {

    @Autowired
    //@Resource 这里得将他的实现jar包引进来到pom。
    UmsMemberService umsMemberService;

    @RequestMapping("/test")
    public String test(){
        return "---";
    }


    @RequestMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterDTO umsMemberRegisterDTO) {
        umsMemberService.register(umsMemberRegisterDTO);
        return ResultWrapper.getSuccessResultWrapperBuilder().data("").build();
    }

    @RequestMapping("/login")
    public String login(@RequestBody UmsMemberLoginDTO umsMemberLoginDTO) {
        Boolean login = umsMemberService.login(umsMemberLoginDTO);
        return "hello: " + login;
    }
}
