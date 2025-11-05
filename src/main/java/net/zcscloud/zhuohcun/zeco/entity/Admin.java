package net.zcscloud.zhuohcun.zeco.entity;

import javax.persistence.Column;

public class Admin extends AbstractUser {
    @Column
    protected Integer role=1;

    public String getInfo() {  //!!Decorator pattern
        return super.getInfo()+"admin.";
    }
}
