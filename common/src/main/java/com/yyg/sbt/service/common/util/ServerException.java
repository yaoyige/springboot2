package com.yyg.sbt.service.common.util;

import lombok.Data;

/**
 * 服务器异常
 */
@Data
public class ServerException extends RuntimeException {
        private Integer code = 500;

        public ServerException(String msg, Throwable cause) {
            super(msg, cause);
        }

        public ServerException(Integer code, String msg, Throwable cause) {
            super(msg, cause);
            this.code = code;
        }
}
