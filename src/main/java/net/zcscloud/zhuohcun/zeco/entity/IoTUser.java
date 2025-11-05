package net.zcscloud.zhuohcun.zeco.entity;

import javax.persistence.Column;

public class IoTUser extends AbstractUser {
    @Column
    protected Integer role=3;
    public String getInfo() {  //!!Decorator pattern
        return super.getInfo()+"IoTUser.";
    }
}
