package net.zcscloud.zhuohcun.zeco.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@Inheritance
@Table(name = "user")
public class Root extends AbstractUser {
    @Column
    protected Integer role=0;

    @Override
    public String getInfo() {  //!!Decorator pattern
        return super.getInfo()+"root.";
    }
}
