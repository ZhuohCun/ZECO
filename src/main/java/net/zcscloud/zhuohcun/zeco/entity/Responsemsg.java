package net.zcscloud.zhuohcun.zeco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Responsemsg {
    private Integer code; // 响应码，200 代表成功; 0 或其他代表失败/业务错误
    private String msg;   // 响应信息 描述字符串
    private Object data;  // 返回的数据

    public Responsemsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
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