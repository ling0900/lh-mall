package com.lh.mall.portal.web.controller.user;

import com.lh.mall.ums.entity.UmsMember;
import com.lh.mall.ums.entity.dto.UmsMemberLoginDTO;
import com.lh.mall.ums.entity.dto.UmsMemberRegisterDTO;
import com.lh.mall.ums.service.UmsMemberService;
import com.lhcommon.base.annotation.TokenCheck;
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

    /**
     * @Resource 这里得将他的实现jar包引进来到pom。 这里需要将service和对应的impl一起导入包！
     */
    @Autowired
    UmsMemberService umsMemberService;

    @RequestMapping("/test")
    public void test() {
        UmsMember umsMember = new UmsMember();
        umsMember.getNickName().toString();
    }

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    @RequestMapping("getCaptcha")
    public String getCaptcha() {
        return "";
    }

    /**
     * @param umsMemberRegisterDTO 验证码、用户信息
     * @return
     */
    @RequestMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterDTO umsMemberRegisterDTO) {
        umsMemberService.register(umsMemberRegisterDTO);
        return ResultWrapper.getSuccessResultWrapperBuilder().data("").build();
    }

    @RequestMapping("/login")
    public String login(@RequestBody @Valid UmsMemberLoginDTO umsMemberLoginDTO) {
        String login = umsMemberService.login(umsMemberLoginDTO);
        return "登陆结果: " + login;
    }

    /**
     * 思考如何针对字段接口定制校验字段？
     *
     * @param umsMemberLoginDTO
     * @return
     */
    @RequestMapping("/verifyToken")
    public String verifyToken(@RequestBody UmsMemberLoginDTO umsMemberLoginDTO) {
        boolean b = umsMemberService.verifyToken(umsMemberLoginDTO);
        return "结果：" + b;
    }

    @RequestMapping("/modify")
    @TokenCheck
    public ResultWrapper modify(@RequestBody UmsMember umsMember) {
        return umsMemberService.modify(umsMember);
    }
}
