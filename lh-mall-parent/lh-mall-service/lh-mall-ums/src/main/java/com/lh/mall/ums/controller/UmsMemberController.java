package com.lh.mall.ums.controller;


import com.lh.mall.ums.entity.UmsMember;
import com.lh.mall.ums.mapper.UmsMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author 孟令浩
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/ums-member")
public class UmsMemberController {
    @Autowired
    UmsMemberMapper umsMemberMapper;

    @RequestMapping("/getAll")
    void test(){
        UmsMember all = umsMemberMapper.getAll();
    }
}

