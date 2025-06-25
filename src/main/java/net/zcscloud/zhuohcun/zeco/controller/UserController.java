package net.zcscloud.zhuohcun.zeco.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Claims;
import net.zcscloud.zhuohcun.zeco.DaoProxy.UserProxy;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "The Controller of Users")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserController{
    @Autowired
    private UserService userService;
    @Autowired
    private UserProxy userProxy;

    @PostMapping("/login")
    public String login(String requestusername,String requestpassword) {
        String username = requestusername;
        String password = requestpassword;
        if (username == null) {
            return "-1";
        }
        if (password == null) {
            return "-1";
        }
        return userService.login(username,password);
    }
    @PostMapping("/verifyToken")
    public String verifyToken( String token) {
        if (token != null) {
            return userService.verifyToken(token);
        } else {
            return "-1"; //invalid
        }
    }
    @PostMapping("/lock")  //Only root can perform this operation
    public String lock( String token,  String usid) {
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return "-2";
        }
        if(userService.verifyToken(token)=="1" && (role==0)){  //verification
            try {
                return userService.lock(usid, gottenid);
            }catch (Exception e){
                return "-3";
            }
        }else {
            return "-4";
        }
    }

    @PostMapping("/unlock")  //Only root can perform this operation
    public String unlock( String token,  String usid) {
        String gottenid = null;
        int role;
        try{
            Claims c = JwtUtil.extractAllClaims(token);
            gottenid=c.getId();
            role=userService.getrole(gottenid);
        }catch(Exception e){
            return "-1";
        }
        if(userService.verifyToken(token)=="1" && (role==0)){  //verification
            try {
                return userService.unlock(usid, gottenid);
            }catch (Exception e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }

    @GetMapping("/getDescription")  //Decorator pattern
    public String getDescription( String token) {
        if(userService.verifyToken(token)=="1"){
            try{
                Claims c = JwtUtil.extractAllClaims(token);
                int usid=Integer.parseInt(c.getId());
                return userService.getDescription(usid);
            }catch (Exception e){
                return "-1";
            }
        }else {
            return "-1";
        }
    }
}

