package com.lh.mall.portal.web.interceptor;

import com.lh.mall.util.EDE.JwtUtil;
import com.lhcommon.base.annotation.TokenCheck;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这个就本地测试写一下
        log.info("进入拦截器");
        /* 有了token后，下一步操作。handler，真正的处理方法在handler。
        这句话结合28行注释理解：把注解加载了谁的上面，handlerMethod就是代表具体的那个方法。 */
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查方法上是否有TokenCheck注解
        if (method.isAnnotationPresent(TokenCheck.class)) {
            //获得注解
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            // 如果含有注解TokenCheck
            if (annotation.required()) {
                // 校验token
                try {
                    // 获取token，一般把token放在header里面的。
                    String token = request.getHeader("token");
                    if (StringUtils.isBlank(token)) {
                        log.info("token不存在");
                        log.error("token不存在");
                        // return false;
                        throw new LoginException("token为空");
                    }
                    JwtUtil.parseToken(token);
                    // 此处的逻辑是，如果解析token能够不出错，则认为通过了校验。
                    return true;
                } catch (Exception e) {
                    log.error("token解析失败了" + e.toString());
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
