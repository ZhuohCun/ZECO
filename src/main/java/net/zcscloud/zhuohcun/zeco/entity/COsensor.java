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
public class COsensor extends AbstractDevice {
    @Column
    String type="1";

    @Column
    String max_value= "100";

    @Column
    String unit= "%";

    @Override
    public String getInfo(){  //!!Decorator pattern
        return super.getInfo()+" the CO value and the max scale is "+max_value+" and unit is "+unit+".";
    }
}
