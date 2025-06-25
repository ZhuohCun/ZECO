package net.zcscloud.zhuohcun.zeco.controller;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.AbstractDevice;
import net.zcscloud.zhuohcun.zeco.entity.Place;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.service.DeviceService;
import net.zcscloud.zhuohcun.zeco.service.PlaceService;
import net.zcscloud.zhuohcun.zeco.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Api(tags = "The Controller of Devices")
@RestController
@RequestMapping("/api/device")
@CrossOrigin(origins = "*")
public class DeviceController{

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/updateValue")  //!!Observer Pattern
    public String updateValue( String id,  String token,  String value) throws IOException {
        if(userService.verifyToken(token)=="1"){
            try {
                return deviceService.updateValue(id, value,value);
            }catch (Exception e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }
    @PostMapping("/updateDeviceSpecs")  //Only admin (or up) can perform this operation
    public String updateDeviceSpecs( int devid, String token, String name, String type, String unit, String maxvalue, String physicalid, String placeid) throws IOException {
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return "-1";
        }
        if(userService.verifyToken(token)=="1" && (role==1||role==0)){  //verification
            try {
                return deviceService.updateDeviceSpecs(devid, name, type, unit, maxvalue, physicalid, placeid);
            }catch (Exception e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }
    @PostMapping("/DeleteDevice")  //Only admin/root can perform this operation
    public String deleteDevice( String token, String devid) throws IOException {
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return "-1";
        }
        if(userService.verifyToken(token)=="1" && (role==1||role==0)){  //verification
            try {
                return deviceService.deleteDevice(devid, gottenid);  //!!demeter Law  /verification success, only provide necessary information
            }catch (Exception e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }
    @GetMapping("/getDevices")
    public List<deviceresponsebody> getDevices(String token, String placeid) throws IOException {
        if(userService.verifyToken(token)=="1"){
            try{
                List<AbstractDevice> devices =  deviceService.getDevices(placeid);
                List<deviceresponsebody> responselist = new ArrayList<>();
                devices.forEach(device -> {
                    responselist.add(new deviceresponsebody(device.getId(),device.getName(),device.getType(),device.getUnit(),device.getCvalue(),device.getCcondition(),device.getColor(),device.getId$place()));
                });
                return responselist;
            }catch (IOException e){
                return null;
            }
        }else {
            return null;
        }
    }
    @GetMapping("/getDescription")  //Decorator pattern
    public String getDescription( String token, String devid) {
        if(userService.verifyToken(token)=="1"){
            return deviceService.getDescription(Integer.parseInt(devid));
        }else {
            return "-1";
        }
    }
    @GetMapping("/getUpdate")
    public String getUpdate( String token, String devid) throws IOException {
        if(userService.verifyToken(token)=="1"){
            try{
                return deviceService.getUpdate(devid);
            }catch (IOException e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }
}
@Getter
class deviceresponsebody{
    private int id;
    private String name;
    private int type;
    private String unit;
    private String cvalue;
    private String condition;
    private String color;
    private int id$place;

    public deviceresponsebody(int id, String name,int type, String unit, String cvalue, String condition, String color, int id$place) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.cvalue = cvalue;
        this.condition = condition;
        this.color = color;
        this.id$place = id$place;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getType() {
        return type;
    }
    public String getUnit() {
        return unit;
    }
    public String getCvalue() {
        return cvalue;
    }
    public String getCondition() {
        return condition;
    }
    public String getColor() {
        return color;
    }
    public int getId$place() {
        return id$place;
    }
}

