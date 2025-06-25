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
@Table(name = "device")
public class PM25sensor extends AbstractDevice {
    @Column
    String type="3";

    @Column
    String max_value= "50";

    @Column
    String unit= "mg/m3";

    @Override
    public String getInfo(){  //!!Decorator pattern
        return super.getInfo()+" the PM2.5 value and the max scale is "+max_value+" and unit is "+unit+".";
    }
}