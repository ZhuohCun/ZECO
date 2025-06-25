package net.zcscloud.zhuohcun.zeco.service;

import io.jsonwebtoken.Claims;
import net.zcscloud.zhuohcun.zeco.DaoProxy.DeviceProxy;
import net.zcscloud.zhuohcun.zeco.DaoProxy.UserProxy;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;

@Service
@EnableScheduling
public class DeviceService{

    @Autowired
    DeviceProxy deviceProxy;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private UserProxy userProxy;

    public String updateValue(String devid, String token,String value) throws JSONException, IOException { //!!Observer Pattery //Only IoT user can update 「its」 value
        synchronized (this) {
            if(ismatch(devid,token)==1){
                try{
                    deviceProxy.updateDeviceValue(Integer.parseInt(devid),value);
                    return "1";
                }catch (Exception e){
                    return "-1";
                }
            }else {
                return "-1";
            }
        }
    }
    public String updateDeviceSpecs(int devid,String name,String type,String unit,String maxvalue,String physicalid,String placeid) throws JSONException, IOException { //Only root/admin can update 「its」 value
        synchronized (this) {
            deviceProxy.updateDeviceSpecs(devid, name, type, unit, maxvalue, physicalid, placeid);
            return  "1";
        }
    }
    public String deleteDevice(String devid,String id) throws JSONException, IOException {
        synchronized (this) {
            deviceProxy.deleteDevice(devid, id);
            return  "1";
        }
    }
    public List<AbstractDevice> getDevices(String placeid) throws JSONException, IOException {
        return deviceProxy.getdevicesbyplaceid(Integer.parseInt(placeid));
    }
    public int ismatch(String devid,String token) throws JSONException, IOException {
        if(userService.verifyToken(token)=="0"){
            Claims c=jwtUtil.extractAllClaims(token);
            if(c.get("userid").equals(deviceProxy.getusidbydevid(Integer.parseInt(devid))) && userProxy.getuserrole((Integer) c.get("usid"))==3){  //Confirm that the extracted userid is identical with the stored userid and that it's an IoT user.
                return 1;//validation succeeded
            }else {
                return -1;//validation failed
            }
        }else{
            return -1; //validation failed
        }
    }

    public String getDescription(int devid) {  //!!Decorator pattern
        AbstractDevice specificDevice=deviceProxy.getDevicebyId(devid);
        if(specificDevice.getType()==1){
            specificDevice=new COsensor();
        }else if(specificDevice.getType()==2){
            specificDevice=new TemperatureSensor();
        }else if(specificDevice.getType()==3){
            specificDevice=new PM25sensor();
        }else {
            return "-1";
        }
        return specificDevice.getInfo();
    }

    public String getUpdate(String devid) throws IOException  {
        return deviceProxy.getUpdate(Integer.parseInt(devid));
    }
}
