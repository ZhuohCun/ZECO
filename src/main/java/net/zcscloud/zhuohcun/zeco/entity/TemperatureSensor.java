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
public class TemperatureSensor extends AbstractDevice {
    @Column
    String type="2";

    @Column
    String maxvalue= "60";

    @Column
    String unit= "℃";
    @Override
    public String getInfo(){  //!!Decorator pattern
        return super.getInfo()+" the temperature value and the max scale is "+maxvalue+" and unit is "+unit+".";
    }
}
