package net.zcscloud.zhuohcun.zeco.entity;

import net.zcscloud.zhuohcun.zeco.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
public abstract class AbstractUser extends LogicEntity {
    @Column
    protected String username;

    @Column
    protected String password;

    @Column
    protected int role;  //0.root 1.admin 2.user 3.IoTdevice

    @Column
    protected String phone;

    @Column
    protected String email;

    @Column
    protected int islocked;  //is the user locked

    @Column
    protected int lockedby;  //who locked the user

    public String getInfo() {  //!!Decorator pattern
        return "This is a ZECO user with the role of ";
    }
}
