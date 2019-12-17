package com.yyg.sbt.service.common.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.*;

/**
 * @author yoayige
 * @since 2019-06-23 13:41
 */
@Configuration
@Profile({"dev", "test", "pre","uat"})
public class ShowSqlConfig {
    /**
     * SQL执行效率插件
     * 设置 dev test 环境开启
     */
    @Bean
    public PerformanceInterceptor performanceInterceptorPlus() {
        // 参数：maxTime SQL 执行最大时长，超过自动停止运行，有助于发现问题。
        // 参数：format SQL SQL是否格式化，默认false
        return new PerformanceInterceptorPlus();
    }

    /**
     * 打印完整SQL
     */
    @Intercepts({
            @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
            @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
            @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
    })
    @Slf4j
    static class PerformanceInterceptorPlus extends PerformanceInterceptor {

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            // 找statement
            Statement statement;
            Object firstArg = invocation.getArgs()[0];
            if (Proxy.isProxyClass(firstArg.getClass())) {
                statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
            } else {
                statement = (Statement) firstArg;
            }
            MetaObject stmtMetaObj = SystemMetaObject.forObject(statement);
            try {
                statement = (Statement) stmtMetaObj.getValue("stmt.statement");
            } catch (Exception e) {
                // do nothing
            }

            // 没有找到直接返回
            if (statement == null) {
                return invocation.proceed();
            }

            String originalSql = statement.toString().replaceAll("[\\s]+", StringPool.SPACE);
            int index = indexOfSqlStart(originalSql);
            if (index > 0) {
                originalSql = originalSql.substring(index);
            }

            // 计算执行 SQL 耗时
            long start = SystemClock.now();
            Object result = invocation.proceed();
            long timing = SystemClock.now() - start;

            // 格式化 SQL 打印执行结果
            Object target = PluginUtils.realTarget(invocation.getTarget());
            MetaObject metaObject = SystemMetaObject.forObject(target);
            MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

            String formatSql = " Time：" + timing +
                    " ms - ID：" + ms.getId() +
                    StringPool.NEWLINE + "Execute SQL：" +
                    SqlUtils.sqlFormat(originalSql, true) + StringPool.NEWLINE;
            log.info(formatSql);
            return result;
        }


        /**
         * 获取sql语句开头部分
         *
         * @param sql ignore
         * @return ignore
         */
        private int indexOfSqlStart(String sql) {
            String upperCaseSql = sql.toUpperCase();
            Set<Integer> set = new HashSet<> ();
            set.add(upperCaseSql.indexOf("SELECT "));
            set.add(upperCaseSql.indexOf("UPDATE "));
            set.add(upperCaseSql.indexOf("INSERT "));
            set.add(upperCaseSql.indexOf("DELETE "));
            set.remove(-1);
            if (CollectionUtils.isEmpty(set)) {
                return -1;
            }
            List<Integer> list = new ArrayList<> (set);
            list.sort(Comparator.naturalOrder());
            return list.get(0);
        }
    }
}
