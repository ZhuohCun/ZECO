package net.zcscloud.zhuohcun.zeco.entity;

import net.zcscloud.zhuohcun.zeco.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
public class Place extends LogicEntity {
    @Column
    protected String name;  //place name

    @Column
    protected String address;  //the address of a specific place

    @Column
    protected String description;  //place description
}
