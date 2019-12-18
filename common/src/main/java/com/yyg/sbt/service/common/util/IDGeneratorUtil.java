package com.yyg.sbt.service.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * @author yyg
 * @since 2019/1/7 下午8:54
 */
public class IDGeneratorUtil {

    private static StringRedisTemplate template = SpringContextUtil.getBean(StringRedisTemplate.class);

    /**
     * 通过Redis获取自增序号
     *
     * @param pre       前缀
     * @param length    序号长度
     * @param increment 是否自增
     * @param isDate    是否只获取年月
     * @return 前缀+日期+序列号
     */
    public static String getRedisNo(String pre, int length, boolean increment, boolean isDate) {
        return getRedisNo(pre, "", length, increment, isDate);
    }

    /**
     * 通过Redis获取自增序号
     *
     * @param pre       前缀
     * @param middle    中间数
     * @param length    序号长度
     * @param increment 是否自增
     * @param isDate    是否只获取年月
     * @return 前缀+中间数+日期+序列号
     */
    public static String getRedisNo(String pre, String middle, int length, boolean increment, boolean isDate) {
        return pre + buildRedisNo(pre, middle, length, increment, isDate);
    }

    /**
     * 通过Redis获取自增序号(不带前缀)
     *
     * @param pre       前缀
     * @param length    序号长度
     * @param increment 是否自增
     * @param isDate    是否只获取年月
     * @return 前缀+中间数+日期+序列号
     */
    public static String getRedisNoWithoutPre(String pre, String middle, int length, boolean increment, boolean isDate) {
        return buildRedisNo(pre, middle, length, increment, isDate);
    }

    private static String buildRedisNo(String pre, String middle, int length, boolean increment, boolean isDate) {
        // 日期
        String date;
        if (isDate) {
            date = LocalDate.now().getYear() + "" + LocalDate.now().getMonthValue();
        } else {
            date = LocalDate.now().format(DateTimeFormatter.ISO_DATE).replace("-", "");
        }

        // 编号
        String no;
        if (increment) {
            // 自增获取
            Long temp = template.opsForValue().increment(pre + date, 1);
            if (temp == 1) {
                // 过期时间
                template.expire(pre + date, isDate ? 30 : 1, TimeUnit.DAYS);
            }
            no = temp.toString();
        } else {
            // 非自增获取
            no = template.opsForValue().get(pre + date);
            if (StringUtils.isEmpty(no)) {
                no = template.opsForValue().increment(pre + date, 1).toString();
                template.expire(pre + date, isDate ? 30 : 1, TimeUnit.DAYS);
            }
        }

        if (no.length() >= length) {
            return middle + date + no;
        } else {
            // 补齐
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < length - no.length(); i++) {
                temp.append("0");
            }
            return middle + date + temp.toString() + no;
        }
    }

}

