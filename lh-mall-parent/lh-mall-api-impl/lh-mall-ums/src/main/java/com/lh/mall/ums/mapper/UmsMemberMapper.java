package com.lh.mall.ums.mapper;

import com.lh.mall.ums.entity.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.mall.ums.entity.dto.UmsMemberRegisterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author 孟令浩
 * @since 2022-05-24
 */
@Mapper
//@Repository 这个注解会报错
public interface UmsMemberMapper extends BaseMapper<UmsMember> {
    UmsMember getAll();
    UmsMember selectByName(String name);
}
