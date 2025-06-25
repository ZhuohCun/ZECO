package net.zcscloud.zhuohcun.zeco.controller;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.Place;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.service.PlaceService;
import net.zcscloud.zhuohcun.zeco.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Api(tags = "The Controller of Places")
@RestController
@RequestMapping("/api/place")
@CrossOrigin(origins = "*")
public class PlaceController{
    @Autowired  //!!Singleton design pattern
    private UserService userService;
    @Autowired  //!!Singleton design pattern
    private PlaceService placeService;
    @GetMapping("/getPlacesList")
    public List<placeresponsebody> getPlacesList(String token) throws IOException{
        if(userService.verifyToken(token)=="1"){
            try{
                List<Place> places =  placeService.getPlacesList();
                List<placeresponsebody> responselist = new ArrayList<>();
                places.forEach(place -> {
                    responselist.add(new placeresponsebody(place.getId(),place.getName(),place.getAddress(),place.getDescription()));
                });
                return responselist;
            }catch (IOException e){
                return null;
            }
        }else {
            return null;
        }
    }
    @PostMapping("/updatePlaceInfo")
    public String updatePlaceInfo( String token, String placeid, String name, String description, String address) throws IOException{
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return "-1";
        }
        if(userService.verifyToken(token)=="1" && (role==1||role==0)){  //verification, only root and admin can perform this operation
            try {
                placeService.updatePlaceInfo(placeid,name,description,address);
                return "1";
            }catch (Exception e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }
}
@Getter
class placeresponsebody{
    private int id;
    private String name;
    private String address;
    private String description;

    public placeresponsebody(int id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public placeresponsebody() {
    }
    public int getId() {
        return id;
    }
    public  String getName() {
        return name;
    }
    public  String getAddress() {
        return address;
    }
    public  String getDescription() {
        return description;
    }
}
