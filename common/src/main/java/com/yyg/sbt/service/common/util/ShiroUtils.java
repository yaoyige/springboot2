package com.yyg.sbt.service.common.util;

import com.yyg.sbt.service.common.enums.SysUserEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 应用模块名称<p>Shiro工具类
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 Bytedance，Inc. All rights reserved. <p>
 * Company: 北京字节跳动科技有限公司 Enterprise Application <p>
 *
 * @author huliangshan
 * @since 2019/1/14 15:26
 */
//@Component
public class ShiroUtils {

    private static String autoLogin;

    @Value("${sso.autoLogin}")
    public void setAutoLogin(String autoLogin) {
        ShiroUtils.autoLogin = autoLogin;
    }

    /**
     * 循环次数
     */
    public static final  int  HASH_ITERATIONS = 1;
    /**
     * 加密算法
     */
    public  static final String  HASH_ALGORITHM_NAME = "MD5";

    private ShiroUtils(){

    }

    public static String md5(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserInfo getUserInfo() {
        if("supplier".equals(autoLogin)){
            return (UserInfo) SecurityUtils.getSubject().getPrincipal();
        }else if("inner".equals(autoLogin)){
            return ContextUtil.getInstance().getCurrentUser();
        }else{
            UserInfo userInfo = new UserInfo();
            userInfo.setEmployeeId("123456");
            userInfo.setUserName("测试用户");
            userInfo.setEmail("123456@139.com");
            userInfo.setUserType(SysUserEnum.UseTypeEnum.INNER.getCode());
            userInfo.setName(userInfo.getUserName());
            return userInfo;
        }
    }

    public static String getUserId() {
        return getUserInfo().getEmployeeId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

}
