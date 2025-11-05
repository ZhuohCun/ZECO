package net.zcscloud.zhuohcun.zeco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responsemsg {
    private Integer code;  //response code
    private String msg;  //response message
    private Object data;  //response data

    public static Responsemsg success(Object data){
        return new Responsemsg(200,"success",data);
    }
    public static Responsemsg error(Object data) {return new Responsemsg(400,"error",data);}
}
