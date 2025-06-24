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

    public Responsemsg login(String username, String password) {
        AbstractUser user = userProxy.findByName(username);
        if (user == null) {
            return Responsemsg.error("-1");
        }
        if (!user.getPassword().equals(password)) {
            return Responsemsg.error("-1");
        }
        if(user.getIsdeleted()==1&&user.getIslocked()==1){
            return Responsemsg.error("-2");
        }
        return Responsemsg.successWithMessage(JwtUtil.generateToken(username,43200000));
    }
    public String verifyToken(String token) {
        try {
            if (JwtUtil.validateToken(token)) {
                return "0"; //valid
            }
            return "1"; //overdue
        } catch (Exception e) {
            return "2"; //error occurred
        }
    }
    public int getrole (String id) {
        return userProxy.getuserrole(Integer.parseInt(id));
    }

    public Responsemsg lock(String usid, String gottenid) {
        synchronized (this) {
            return  userProxy.lock(usid,gottenid);
        }
    }
    public Responsemsg unlock(String usid, String gottenid) {
        synchronized (this) {
            return  userProxy.unlock(usid,gottenid);
        }
    }

    public String getDescription(int usid) {
        User specificUser=userProxy.getUserbyId(usid);
        return specificUser.getInfo();
    }
}