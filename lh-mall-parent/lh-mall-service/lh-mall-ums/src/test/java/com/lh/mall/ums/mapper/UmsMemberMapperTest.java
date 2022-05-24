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
        UmsMember u = new UmsMember();
        // 有了递增，数据库，set的id也不会生效的。
        u.setId(2L);
        u.setUsername("wu0Name");
        umsMemberMapper.insert(u);
    }
}