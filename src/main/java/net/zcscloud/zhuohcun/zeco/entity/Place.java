package net.zcscloud.zhuohcun.zeco.entity;

import lombok.Data;
import net.zcscloud.zhuohcun.zeco.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Inheritance
public class Place extends LogicEntity {
    @Column
    protected String name;  //place name

    @Column
    protected String address;  //the address of a specific place

    @Column
    protected String description;  //place description

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public String getDescription() {
        return description;
    }
}
