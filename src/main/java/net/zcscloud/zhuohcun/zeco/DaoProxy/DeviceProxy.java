package net.zcscloud.zhuohcun.zeco.DaoProxy;

import net.zcscloud.zhuohcun.zeco.dao.DeviceDao;
import net.zcscloud.zhuohcun.zeco.entity.AbstractDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceProxy implements GeneralProxy{  //!!Proxy Pattern
    @Autowired
    private DeviceDao deviceDao;

    public void updateDeviceValue(int devid, String value){
        float max=deviceDao.getmaxvaluebydevid(devid);
        float percent=Integer.parseInt(value)/max;
        String color;
        String condition;
        if(percent<0.3){
            condition="Excellent";
            color="green";
        } else if (percent<=0.5) {
            condition="Good";
            color="green";
        }else if (percent<=0.65){
            condition="Bad";
            color="red";
        } else {
            condition="Awful";
            color="red";
        }
        deviceDao.updateDeviceValue(devid, value, color, condition);
    }

    public String getusidbydevid(int devid){
        return String.valueOf(deviceDao.getusidbydevid(devid));
    }

    public List<AbstractDevice> getdevicesbyplaceid(int placeid){
        return deviceDao.getdevicesbyplaceid(placeid);
    }
    public void updateDeviceSpecs(int devid, String name, String type, String unit, String maxvalue, String physicalid, String placeid){
        deviceDao.updateDeviceSpecs(devid, name, Integer.parseInt(type), unit, Float.valueOf(maxvalue), physicalid, Integer.parseInt(placeid));
    }

    public void deleteDevice(String devid, String id) {
        deviceDao.deleteDevice(Integer.parseInt(devid), Integer.parseInt(id));
    }

    public AbstractDevice getDevicebyId(int devid) {
        return deviceDao.getDevicebyId(devid);
    }

    public String getUpdate(int devid) {
        return String.valueOf(deviceDao.getUpdate(devid));
    }
}
