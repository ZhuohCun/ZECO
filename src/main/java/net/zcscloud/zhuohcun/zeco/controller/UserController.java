package net.zcscloud.zhuohcun.zeco.controller;
import io.jsonwebtoken.Claims;
import net.zcscloud.zhuohcun.zeco.DaoProxy.UserProxy;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.AbstractUser;
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
    public String login(@RequestBody String requestusername, @RequestBody String requestpassword) {
        String username = requestusername;
        String password = requestpassword;
        AbstractUser user = userProxy.findByName(username);
        if (user == null) {
            return "error";
        }
        if (!user.getPassword().equals(password)) {
            return "error";
        }
        return userService.login(user);
    }
    @PostMapping("/verifyToken")
    public String verifyToken(@RequestBody String requesttoken) {
        String token = requesttoken;
        if (token != null && !token.isEmpty()) {
            return userService.verifyToken(token);
        } else {
            return "2"; //invalid
        }
    }
    @PostMapping("/lock")  //Only admin/root can perform this operation
    public Responsemsg lock(@RequestBody String token, @RequestBody String usid) {
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
                return userService.lock(usid, gottenid);
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }

    @PostMapping("/unlock")  //Only admin/root can perform this operation
    public Responsemsg unlock(@RequestBody String token, @RequestBody String usid) {
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
                return userService.unlock(usid, gottenid);
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }

    @GetMapping("/getDescription")  //Decorator pattern
    public Responsemsg getDescription(@RequestBody String token) {
        if(userService.verifyToken(token)=="0"){
            try{
                Claims c = JwtUtil.extractAllClaims(token);
                int usid=Integer.parseInt(c.getId());
                return Responsemsg.success(userService.getDescription(usid));
            }catch (Exception e){
                return Responsemsg.error("-1");
            }
        }else {
            return Responsemsg.error("-1");
        }
    }
}

