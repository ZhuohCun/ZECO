package net.zcscloud.zhuohcun.zeco.service;

import net.zcscloud.zhuohcun.zeco.DaoProxy.UserProxy;
import net.zcscloud.zhuohcun.zeco.common.JwtUtil;
import net.zcscloud.zhuohcun.zeco.entity.AbstractUser;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    @Autowired
    private UserProxy userProxy;

    public String login(String username, String password) {
        AbstractUser user = userProxy.findByName(username);
        if (user == null) {
            return "-1";
        }
        if (!user.getPassword().equals(password)) {
            return "-1";
        }
        if(user.getIsdeleted()==1&&user.getIslocked()==1){
            return "-2";
        }
        return JwtUtil.generateToken(username,432000000);
    }
    public String verifyToken(String token) {
        try {
            if (JwtUtil.validateToken(token)) {
                return "1"; //valid
            }
            return "-1"; //overdue
        } catch (Exception e) {
            return "-2"; //error occurred
        }
    }
    public int getrole (String id) {
        return userProxy.getuserrole(Integer.parseInt(id));
    }

    public String lock(String usid, String gottenid) {
        synchronized (this) {
            return  userProxy.lock(usid,gottenid);
        }
    }
    public String unlock(String usid, String gottenid) {
        synchronized (this) {
            return  userProxy.unlock(usid,gottenid);
        }
    }

    public String getDescription(int usid) {
        User specificUser=userProxy.getUserbyId(usid);
        return specificUser.getInfo();
    }
}