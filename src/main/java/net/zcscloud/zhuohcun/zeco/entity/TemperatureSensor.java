package net.zcscloud.zhuohcun.zeco.entity;

import javax.persistence.Column;

public class TemperatureSensor extends AbstractDevice {
    @Column
    String type="2";

    @Column
    String maxvalue= "60";

    @Column
    String unit= "℃";
    public String getInfo(){  //!!Decorator pattern
        return super.getInfo()+" the temperature value and the max scale is "+maxvalue+" and unit is "+unit+".";
    }
}
