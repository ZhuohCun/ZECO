package net.zcscloud.zhuohcun.zeco.entity;

import javax.persistence.Column;

public class User extends AbstractUser {
    @Column
    protected Integer role=2;

    public String getInfo() {  //!!Decorator pattern
        return super.getInfo()+"user.";
    }
}
