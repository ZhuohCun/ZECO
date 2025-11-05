package net.zcscloud.zhuohcun.zeco.entity;
import javax.persistence.Column;

public class Root extends AbstractUser {
    @Column
    protected Integer role=0;

    public String getInfo() {  //!!Decorator pattern
        return super.getInfo()+"root.";
    }
}
