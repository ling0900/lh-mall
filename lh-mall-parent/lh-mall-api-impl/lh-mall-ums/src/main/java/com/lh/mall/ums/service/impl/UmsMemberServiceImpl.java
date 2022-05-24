package com.lh.mall.ums.service.impl;

import com.lh.mall.ums.entity.UmsMember;
import com.lh.mall.ums.entity.dto.UmsMemberLoginDTO;
import com.lh.mall.ums.entity.dto.UmsMemberRegisterDTO;
import com.lh.mall.ums.mapper.UmsMemberMapper;
import com.lh.mall.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 孟令浩
 * @since 2022-05-24
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UmsMemberRegisterDTO umsMemberRegisterDTO) {
        UmsMember umsMember = new UmsMember();
        // 这里不要再写一个新的方法类型了，直接用这个bean的字段转换！
        BeanUtils.copyProperties(umsMemberRegisterDTO, umsMember);
        umsMember.setPassword(passwordEncoder.encode(umsMemberRegisterDTO.getPassword()));
        int i = umsMemberMapper.insert(umsMember);
        return ":" + i;
    }

    @Override
    public Boolean login(UmsMemberLoginDTO umsMemberLoginDTO) {
        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginDTO.getUsername());
        return passwordEncoder.matches(umsMemberLoginDTO.getPassword(), umsMember.getPassword());
    }
}
