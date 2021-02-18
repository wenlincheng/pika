package com.wenlincheng.pika.common.core.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 基础异常
 *
 * @author  wenlincheng
 * @date    2019/11/24 12:37 下午
 * @version 1.0
 */
@Getter
public class PikaException extends RuntimeException {
    private static final long serialVersionUID = -8264841079576589792L;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误类型
     */
    private String type;

    /**
     * 错误信息
     */
    private String msg;

    public PikaException(ErrorCode errorCode) {
        super("code: " + errorCode.getCode() + ", type: " + errorCode.getType() + ", msg: " + errorCode.getMsg());
        this.code = errorCode.getCode();
        this.type = errorCode.getType().type;
        this.msg = msg;
    }

    private PikaException(int code, String type, String msg) {
        super("code: " + code + ", type: " + type + ", msg: " + msg);
        this.code = code;
        this.type = type;
        this.msg = msg;
    }

    private PikaException(int code, String type, String msg, Throwable e) {
        super("code: " + code + ", type: " + type + ", msg: " + msg, e);
        this.code = code;
        this.type = type;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PikaException code: " + code + ", type: " + type + ", msg: " + msg;
    }

    public static Builder<?> construct() {
        return new Builder<>(SystemErrorCodeEnum.SYSTEM_ERROR, null);
    }

    public static Builder<?> construct(Throwable e) {
        return new Builder<>(SystemErrorCodeEnum.SYSTEM_ERROR, e);
    }

    public static <T extends Enum<T> & ErrorCode> Builder<T> construct(T expEnum) {
        return new Builder<>(expEnum, null);
    }

    public static <T extends Enum<T> & ErrorCode> Builder<T> construct(T expEnum, Throwable e) {
        return new Builder<>(expEnum, e);
    }

    /**
     * 构建
     */
    public static class Builder<T extends Enum<T> & ErrorCode> {

        private int code;
        private String type;
        private String msg;
        private StringBuilder msgBuilder;
        private Throwable e;

        private Builder(T expEnum, Throwable e) {
            this.code = expEnum.getCode();
            this.type = expEnum.getType().name();
            this.msg = expEnum.getMsg();
            this.msgBuilder = new StringBuilder();
            this.e = e;
        }

        public Builder<T> setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> appendMsg(String otherMsg) {
            this.msgBuilder.append(otherMsg);
            return this;
        }

        public PikaException build() {
            String msg = this.msg;
            String otherMsg = msgBuilder.toString();
            if (StringUtils.isNotBlank(otherMsg)) {
                if (StringUtils.isNotBlank(msg)) {
                    msg = msg.concat(", ").concat(otherMsg);
                } else {
                    msg = otherMsg;
                }
            }
            if (e == null) {
                return new PikaException(this.code, this.type, msg);
            } else {
                if (e instanceof PikaException) {
                    return (PikaException) e;
                }
                if (e instanceof UndeclaredThrowableException) {
                    Throwable targetException = ((InvocationTargetException) e.getCause()).getTargetException();
                    if (targetException instanceof PikaException) {
                        return (PikaException) targetException;
                    }
                }
                if (e instanceof InvocationTargetException) {
                    Throwable targetException = ((InvocationTargetException) e).getTargetException();
                    if (targetException instanceof PikaException) {
                        return (PikaException) targetException;
                    }
                }
                return new PikaException(this.code, this.type, msg, e);
            }
        }
    }

}
