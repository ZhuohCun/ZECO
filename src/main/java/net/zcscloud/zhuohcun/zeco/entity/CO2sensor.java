package net.zcscloud.zhuohcun.zeco.entity;

import javax.persistence.Column;

public class CO2sensor extends AbstractDevice {
    @Column
    String type="1";

    @Column
    String maxvalue= "100";

    @Column
    String unit= "%";

    public String getInfo(){  //!!Decorator pattern
        return super.getInfo()+" the CO2 value and the max scale is "+maxvalue+" and unit is "+unit+".";
    }
}
