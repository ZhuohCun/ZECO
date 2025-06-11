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
            condition="LOW";
            color="darkblue";
        } else if (percent<=0.7) {
            condition="GREAT";
            color="green";
        }else {
            condition="HIGH";
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
        deviceDao.updateDeviceSpecs(devid, name, Integer.parseInt(type), unit, maxvalue, physicalid, Integer.parseInt(placeid));
    }

    public void deleteDevice(String devid, String id) {
        deviceDao.deleteDevice(devid, Integer.parseInt(id));
    }

    public AbstractDevice getDevicebyId(int devid) {
        return deviceDao.getDevicebyId(devid);
    }
}
