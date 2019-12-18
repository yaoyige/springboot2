package com.yyg.sbt.service.common.util;

import com.google.common.collect.Maps;

import java.util.Map;


/**
 * 应用模块名称<p>上下文信息
 * 代码描述<p>
 * @author yyg
 * @since 2019/1/31 11:29
 */
public class ContextUtil {

    private static final ContextUtil HOLDER = new ContextUtil();

    private ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(Maps::newHashMap);

    public static ContextUtil getInstance() {
        return HOLDER;
    }

    public static String getCurrentUserCode() {
        return HOLDER.getCurrentUser().getEmployeeId();
    }

    public UserInfo getCurrentUser() {
        return (UserInfo) HOLDER.threadLocal.get().get("user");
    }

    public void setCurrentUser(UserInfo userInfo) {
        this.threadLocal.get().put("user", userInfo);
    }

    public UserRightsInfo getUserPermission() {
        return (UserRightsInfo) HOLDER.threadLocal.get().get("userRightInfo");
    }

    public void setUserPermission(UserRightsInfo userRightInfo) {
        this.threadLocal.get().put("userRightInfo", userRightInfo);
    }

    public void cleanLocal() {
        this.threadLocal.get().clear();
    }

    public String get(String key) {
        return (String) this.threadLocal.get().get(key);
    }

    public void put(String key, String value) {
        this.threadLocal.get().put(key, value);
    }
}