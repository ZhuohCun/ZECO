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
@Table(name = "device")
public class COsensor extends AbstractDevice {
    @Column
    String type="1";

    @Column
    String maxvalue= "100";

    @Column
    String unit= "%";

    @Override
    public String getInfo(){  //!!Decorator pattern
        return super.getInfo()+" the CO value and the max scale is "+maxvalue+" and unit is "+unit+".";
    }
}
