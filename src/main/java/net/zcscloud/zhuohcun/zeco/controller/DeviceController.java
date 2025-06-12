package net.zcscloud.zhuohcun.zeco.controller;

import io.jsonwebtoken.Claims;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.service.DeviceService;
import net.zcscloud.zhuohcun.zeco.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = "The Controller of Devices")
@RestController
@RequestMapping("/api/device")
@CrossOrigin(origins = "*")
public class DeviceController{

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    public DeviceController(@Qualifier("deviceService") @Autowired DeviceService service){

    }
    @PostMapping("/updateValue")  //!!Observer Pattern
    public Responsemsg updateValue(@RequestBody String id, @RequestBody String token, @RequestBody String value) throws IOException {
        if(userService.verifyToken(token)=="0"){
            try {
                return deviceService.updateValue(id, value,value);
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
    @PostMapping("/updateDeviceSpecs")  //Only admin (or up) can perform this operation
    public Responsemsg updateDeviceSpecs(@RequestBody int devid,@RequestBody String token,@RequestBody String name,@RequestBody String type,@RequestBody String unit,@RequestBody String maxvalue,@RequestBody String physicalid,@RequestBody String placeid) throws IOException {
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return Responsemsg.error("-1");
        }
        if(userService.verifyToken(token)=="0" && (role==1||role==0)){  //verification
            try {
                return deviceService.updateDeviceSpecs(devid, name, type, unit, maxvalue, physicalid, placeid);
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
    @PostMapping("/DeleteDevice")  //Only admin/root can perform this operation
    public Responsemsg deleteDevice(@RequestBody String token,@RequestBody String devid) throws IOException {
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return Responsemsg.error("-1");
        }
        if(userService.verifyToken(token)=="0" && (role==1||role==0)){  //verification
            try {
                return deviceService.deleteDevice(devid, gottenid);  //!!demeter Law  /verification success, only provide necessary information
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
    @GetMapping("/getDevices")
    public Responsemsg getDevices(@RequestBody String token, @RequestBody String placeid) throws IOException {
        if(userService.verifyToken(token)=="0"){
            try{
                return Responsemsg.successWithData(deviceService.getDevices(placeid));
            }catch (IOException e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
    @GetMapping("/getDescription")  //Decorator pattern
    public Responsemsg getDescription(@RequestBody String token,@RequestBody String devid) {
        if(userService.verifyToken(token)=="0"){
            return Responsemsg.successWithMessage(deviceService.getDescription(Integer.parseInt(devid)));
        }else {
            return Responsemsg.error("-1");
        }
    }
    @GetMapping("/getUpdate")
    public Responsemsg getUpdate(@RequestBody String token,@RequestBody String devid) throws IOException {
        if(userService.verifyToken(token)=="0"){
            try{
                return Responsemsg.successWithMessage(deviceService.getUpdate(devid));
            }catch (IOException e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
}
