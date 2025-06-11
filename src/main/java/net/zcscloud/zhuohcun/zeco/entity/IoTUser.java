package net.zcscloud.zhuohcun.zeco.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Inheritance
@Table(name = "user")
public class IoTUser extends AbstractUser {
    @Column
    protected Integer role=3;
    public String getInfo() {  //!!Decorator pattern
        return super.getInfo()+"IoTUser.";
    }
}
