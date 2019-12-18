package com.yyg.sbt.service.common.enums;

/**
 * 应用模块名称<p>用户管理枚举类
 * @author yyg
 * @since 2019/1/8 17:51
 */
public class SysUserEnum {
    public enum UseTypeEnum {
        /**
         * 运营111
         */
        INNER("1", "运营"),
        /**
         * 供应商111
         */
        SUPPLIER("2", "供应商");

        String code;

        String value;

        private UseTypeEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum StatusEnum {
        /**
         * 员工
         */
        ENABLE("1", "开启"),
        /**
         * 供应商
         */
        DISABLE("2", "禁用");

        String code;

        String value;

        private StatusEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
