package net.zcscloud.zhuohcun.zeco.entity;

import net.zcscloud.zhuohcun.zeco.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
public abstract class AbstractDevice extends LogicEntity {  //!!Abstract Factory

    @Column
    protected String name;  //the name of the device

    @Column
    protected int type;  //1.CO2 sensor 2.temperature sensor

    @Column
    protected String unit;  //the unit of a specific device

    @Column
    protected String cvalue;  //current value

    @Column
    protected String ccondition;  //current condition //LOW, GREAT OR HIGH

    @Column
    protected float maxvalue;  //max value (used when calculating percentage)

    @Column
    protected float percent;  //percentage (used when generating its condition and color)

    @Column
    protected String color;  //displayed color of a specific device

    @Column
    protected String physicalid;  //serial number of a specific device

    @Column
    protected int id$places;  //the place a specific device is belonged to

    @Column
    protected int id$user;  //the userid of the IoT user

    public String getInfo(){  //!!Decorator pattern
        return "This is the ZECO detector who is detecting";
    }
}
