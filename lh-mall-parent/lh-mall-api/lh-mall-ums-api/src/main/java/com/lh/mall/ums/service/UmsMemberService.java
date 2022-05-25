package com.lh.mall.ums.service;

import com.lh.mall.ums.entity.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.mall.ums.entity.dto.UmsMemberLoginDTO;
import com.lh.mall.ums.entity.dto.UmsMemberRegisterDTO;
import com.lhcommon.base.result.ResultWrapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 孟令浩
 * @since 2022-05-24
 */
public interface UmsMemberService extends IService<UmsMember> {
    String register(UmsMemberRegisterDTO umsMemberRegisterDTO);
    String login(UmsMemberLoginDTO umsMemberLoginDTO);
    boolean verifyToken(UmsMemberLoginDTO umsMemberLoginDTO);
    ResultWrapper modify(UmsMember umsMember);
}
