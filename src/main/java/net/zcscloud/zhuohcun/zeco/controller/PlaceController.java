package net.zcscloud.zhuohcun.zeco.controller;

import io.jsonwebtoken.Claims;
import net.zcscloud.zhuohcun.zeco.DaoProxy.PlaceProxy;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.service.PlaceService;
import net.zcscloud.zhuohcun.zeco.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Api(tags = "The Controller of Places")
@RestController
@RequestMapping("/api/place")
@CrossOrigin(origins = "*")
public class PlaceController{
    @Autowired  //!!Single Existence Pattern
    private UserService userService;
    @Autowired
    private PlaceProxy placeProxy;
    @Autowired
    private PlaceService placeService;

    @GetMapping("/getPlacesList")
    public Responsemsg getPlacesList(@RequestBody String token) throws IOException{
        if(userService.verifyToken(token)=="0"){
            try{
                return Responsemsg.success(placeService.getPlacesList());
            }catch (IOException e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
    @PostMapping("/updatePlaceInfo")
    public Responsemsg updatePlaceInfo(@RequestBody String token,@RequestBody String placeid,@RequestBody String name,@RequestBody String description,@RequestBody String address) throws IOException{
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
                placeService.updatePlaceInfo(placeid,name,description,address);
                return Responsemsg.success("1");
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
}