package com.yyg.sbt.service.common.util;
import com.yyg.sbt.service.common.enums.SysUserEnum;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

/**
 * <p>
 * 国际化工具类
 * <p>
 * Copyright: Copyright (C) 2019 Bytedance，Inc. All rights reserved. <p>
 * Company: 北京字节跳动科技有限公司 Enterprise Application <p>
 *
 * @author zhangfan
 * @since 2019/1/7 13:47
 */
@Slf4j
@Component
public class I18nMessageUtil {

    public static final Integer SAVING_LANGUAGE_TIME = 30 * 24 * 60 * 60;
    public static final String LOCALE = "language";
    public static final String PREFIX = "locale:";
    public static final String SUFFIX = "_language";
    /**
     * 功能描述:语言种类前缀
     */
    public static final String EN = "en";
    public static final String ZH = "zh";


    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        I18nMessageUtil.messageSource = messageSource;
    }

    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        I18nMessageUtil.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * @param code：对应文本配置的key.
     * @return 对应地区的语言消息字符串
     */
    public static String getMessage(String code) {
        return getMessage(code, new Object[]{});
    }

    public static String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return getMessage(code, null, defaultMessage, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return getMessage(code, null, "", locale);
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, "", locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public static String getUserLanguage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserInfo userInfo = ShiroUtils.getUserInfo();
        String finalLanguage = null;
        String redisLanguage = null;
        try {
            //1、已经登录成功则取redis中语言
            if (userInfo != null) {
                redisLanguage = stringRedisTemplate.opsForValue().get(PREFIX + userInfo.getEmployeeId() + SUFFIX);
            }
            //2、未登陆时先看cookie中有无语言数据，没有的话按浏览器默认语言
            finalLanguage = CookieUtil.getCookieVal(request, LOCALE);
            if (StringUtils.isBlank(finalLanguage)) {
                //获取浏览器默认语言Accept-Language: en英文,zh-CN简体中文;q=0.9,zh中文;q=0.8
                String language = request.getHeader("Accept-Language");
                if (language.startsWith(EN)) {
                    finalLanguage = EN;
                } else if (language.startsWith(ZH)) {
                    finalLanguage = ZH;
                }
                //如果需要增加其他语言则继续添加判断
            }
        } catch (Exception e) {
            log.error("获取语种失败{}", e);
            finalLanguage = Locale.getDefault().getLanguage();
        }
        //根据登录类型判断
        if (SysUserEnum.UseTypeEnum.INNER.getCode().equals(userInfo == null ? "" : (userInfo.getUserType()))) {
            return Optional.of(redisLanguage).orElse(finalLanguage);
        } else {
            return Optional.of(finalLanguage).orElse(redisLanguage);
        }
    }

}