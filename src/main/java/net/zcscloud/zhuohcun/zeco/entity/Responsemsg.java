package net.zcscloud.zhuohcun.zeco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class Responsemsg<T> {
    private Integer code;
    private String message;
    private Object data;

    public Responsemsg(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Responsemsg success() {
        return new Responsemsg(200, "success", null);
    }

    public static Responsemsg successWithData(Object data) {
        return new Responsemsg(200, "success", data);
    }

    public static Responsemsg successWithMessage(String message) {
        return new Responsemsg(200, message, null);
    }

    public static Responsemsg successWithMessageAndData(String message, Object data) {
        return new Responsemsg(200, message, data);
    }

    public static Responsemsg error(String message) {
        return new Responsemsg(0, message, null);
    }

    public static Responsemsg errorWithData(String message, Object data) {
        return new Responsemsg(0, message, data);
    }
}