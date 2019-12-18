package com.yyg.sbt.service.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 应用模块名称<p>Cookie工具类
 * 代码描述<p>
 * @author yyg
 * @since 2019/1/8 17:53
 */
public class CookieUtil {

    private static final Integer COOKIE_MAX_AGE = 604800000;

    private static final String ENCODE = "UTF-8";

    private CookieUtil() {

    }

    /**
     * <p>Discription:[添加cookie,默认有效期7天]</p>
     * Created on 2015年3月5日
     *
     * @param response
     * @param name
     * @param value
     * @author:[v-huliangshan.ea]
     */
    public static void setCookie(HttpServletResponse response, String name, String value) throws UnsupportedEncodingException {
        setCookie(response, name, value, COOKIE_MAX_AGE);
    }

    /**
     * <p>Discription:[设置带有httponly属性的cookie]</p>
     * Created on 2017年9月8日
     *
     * @param response
     * @param name
     * @param value
     * @author:[hanshixiong]
     */
    public static void setHttpOnlyCookie(HttpServletResponse response, String name, String value) {
        setHttpOnlyCookie(response, name, value, false, "");
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name
     * @param value
     * @param httpOnly
     */
    public static void setHttpOnlyCookie(HttpServletResponse response, String name, String value, boolean httpOnly, String domain) {
        Date d = new Date(System.currentTimeMillis() + COOKIE_MAX_AGE);
        String http = httpOnly ? "HttpOnly" : "";
        StringBuilder cookie = new StringBuilder();
        cookie.append(name + "=" + value);
        cookie.append(";Expires=" + d.toString());
        if (!StringUtils.isEmpty(domain)) {
            cookie.append("; Domain=" + domain);
        }
        cookie.append("; Path=/");
        cookie.append(";" + http);
        response.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * 删除Cookie
     *
     * @param response
     * @param name
     * @param httpOnly
     * @param domain
     */
    @SuppressWarnings("deprecation")
    public static void delHttpOnlyCookie(HttpServletResponse response, String name, boolean httpOnly, String domain) {
        Date d = new Date(System.currentTimeMillis() + COOKIE_MAX_AGE);
        String http = httpOnly ? "HttpOnly" : "";
        StringBuilder cookie = new StringBuilder();
        cookie.append(name + "=" + "");
        cookie.append(";Expires=" + d.toString());
        if (!StringUtils.isEmpty(domain)) {
            cookie.append("; Domain=" + domain);
        }
        cookie.append(";Path=/");
        cookie.append(";maxAge=0");
        cookie.append(";" + http);
        response.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * <p>Discription:[添加回家机制的cookie]</p>
     * Created on 2015年3月5日
     *
     * @param response
     * @param name
     * @param value
     * @author:[v-huliangshan.ea]
     */
    public static void setDialogCookie(HttpServletResponse response, String name, String value) throws UnsupportedEncodingException {
        if (value == null) {
            value = "";
        }
        @SuppressWarnings("deprecation")
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, ENCODE));
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * <p>Discription:[添加cookie，并指定有效期]</p>
     * Created on 2015年3月5日
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @author:[v-huliangshan.ea]
     */
    @SuppressWarnings("deprecation")
    public static void setCookie(HttpServletResponse response, String name, String value, Integer maxAge, boolean httpOnly, boolean secure, String domain) throws UnsupportedEncodingException {
        if (value == null) {
            value = "";
        }
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, ENCODE));
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        if (maxAge != null) {
            if (maxAge != 0) {
                cookie.setMaxAge(maxAge);
            } else {
                cookie.setMaxAge(COOKIE_MAX_AGE);
            }
        }
        if(!StringUtils.isEmpty(domain)){
            cookie.setDomain(domain);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, Integer maxAge) throws UnsupportedEncodingException {
        setCookie(response, name, value, maxAge, false, false, null);
    }

    /**
     * <p>Discription:[获得制定的cookie]</p>
     * Created on 2015年3月5日
     *
     * @param request
     * @param name
     * @return
     * @author:[v-huliangshan.ea]
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    return c;
                }

            }
        }
        return null;
    }

    /**
     * <p>Discription:[获得制定cookie值]</p>
     * Created on 2019年1月6日
     *
     * @param request
     * @param name
     * @return
     * @author:[v-huliangshan.ea]
     */
    @SuppressWarnings("deprecation")
    public static String getCookieVal(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        Cookie cookie = getCookie(request, name);
        return cookie == null ? "" : URLDecoder.decode(cookie.getValue(), ENCODE);
    }

    /**
     * <p>Discription:[删除制定cookie]</p>
     * Created on 2019年1月6日
     *
     * @param request
     * @param response
     * @param name
     * @author:[v-huliangshan.ea]
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    /**
     * 基础数据字典分页查询
     * Create on 2019/1/10 14:53
     *
     * @param
     * @param
     * @return
     * @throw
     * @author huliangshan
     */
//    public static void setUserAndCookie(HttpServletResponse response, UserInfo userInfo) {
//
//        // 登录名
//        CookieUtil.setHttpOnlyCookie(response, SsoConstant.EMPLOYEE_NAME, userInfo.getUserName());
//        // 员工号
//        CookieUtil.setHttpOnlyCookie(response, SsoConstant.EMPLOYEE_ID, userInfo.getEmployeeId());
//    }

    /**
     * 返回错误信息带错误码
     * Create on 2019/1/15 17:37
     *
     * @param response
     * @param errorCode
     * @return errorMessage
     * @author huliangshan
     */
    public static void returnErrorMessage(HttpServletResponse response, Integer errorCode, String errorMessage) throws IOException {
        JSONResult result = new JSONResult();
        result.setCode(errorCode);
        result.setMsg(errorMessage);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.print(mapper.writeValueAsString(result));
        out.flush();
    }
}