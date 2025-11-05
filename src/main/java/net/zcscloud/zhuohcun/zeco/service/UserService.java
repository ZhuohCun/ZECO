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

    public String login(AbstractUser abstractUser) {
        AbstractUser user = userProxy.findByName(abstractUser.getUsername());
        if (user == null) {
            throw new RuntimeException("Username or password is incorrect");
        }
        if (!user.getPassword().equals(abstractUser.getPassword())) {
            throw new RuntimeException("Username or password is incorrect");
        }
        return JwtUtil.generateToken(user.getUsername(),43200000);
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