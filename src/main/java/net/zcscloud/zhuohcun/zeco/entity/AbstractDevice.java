package net.zcscloud.zhuohcun.zeco.entity;

import lombok.Data;
import net.zcscloud.zhuohcun.zeco.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance
@Data
@Table(name = "device")
public class AbstractDevice extends LogicEntity {  //!!Abstract Factory

    @Column
    protected String name;  //the name of the device

    @Column(name = "`type`")
    protected int type;  //1.CO2 sensor 2.temperature sensor 3.PM25 sensor

    @Column
    protected String unit;  //the unit of a specific device

    @Column
    protected String cvalue;  //current value

    @Column
    protected String ccondition;  //current condition //LOW, GREAT OR HIGH

    @Column
    protected float max_value;  //max value (used when calculating percentage)

    @Column(name = "`percent`")
    protected float percent;  //percentage (used when generating its condition and color)

    @Column
    protected String color;  //displayed color of a specific device

    @Column
    protected String physicalid;  //serial number of a specific device

    @Column
    protected int id$place;  //the place a specific device is belonged to

    @Column
    protected int id$user;  //the userid of the IoT user

    public String getInfo(){  //!!Decorator pattern
        return "This is a ZECO detector who is detecting";
    }
    public String getCcondition(){
        return this.ccondition;
    }
    public String getName(){
        return this.name;
    }
    public String getUnit(){
        return this.unit;
    }
    public String getCvalue(){
        return this.cvalue;
    }
    public String getColor(){
        return this.color;
    }
    public int getId$place(){
        return this.id$place;
    }
    public int getId$user(){
        return this.id$user;
    }
    public int getType(){
        return this.type;
    }
}
