package com.lh.mall.ums.mapper;

import com.lh.mall.ums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UmsMemberMapperTest {
    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Test
    void test() {
        String name = "meng";
        UmsMember umsMember = umsMemberMapper.selectByName(name);
        System.out.println(umsMember.toString());
    }
}