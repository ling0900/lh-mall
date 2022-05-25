package com.lh.mall.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.mall.ums.entity.UmsMember;
import com.lh.mall.ums.entity.dto.UmsMemberLoginDTO;
import com.lh.mall.ums.entity.dto.UmsMemberRegisterDTO;
import com.lh.mall.ums.mapper.UmsMemberMapper;
import com.lh.mall.ums.service.UmsMemberService;
import com.lh.mall.util.EDE.JwtUtil;
import com.lhcommon.base.result.ResultWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    //注册
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
    public String login(UmsMemberLoginDTO umsMemberLoginDTO) {
        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginDTO.getUsername());
        boolean matches = passwordEncoder.matches(umsMemberLoginDTO.getPassword(), umsMember.getPassword());

        if (matches) {
            return JwtUtil.createToken(umsMemberLoginDTO.getUsername());
        }

        return "";
    }

    /**
     * 校验 鉴权接口
     * @param umsMemberLoginDTO
     * @return
     */
    @Override
    public boolean verifyToken(UmsMemberLoginDTO umsMemberLoginDTO) {
        String s = JwtUtil.parseToken(umsMemberLoginDTO.getToken());
        System.out.println("================\n" + s);
        return true;
    }

    @Override
    public ResultWrapper modify(UmsMember umsMember) {
        // 业务代码，省略。
        return ResultWrapper.getSuccessResultWrapperBuilder()
                .data(umsMember).build();
    }
}
