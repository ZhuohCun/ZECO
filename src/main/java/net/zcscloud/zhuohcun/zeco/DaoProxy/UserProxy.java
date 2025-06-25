package net.zcscloud.zhuohcun.zeco.DaoProxy;

import net.zcscloud.zhuohcun.zeco.dao.UserDao;
import net.zcscloud.zhuohcun.zeco.entity.AbstractUser;
import net.zcscloud.zhuohcun.zeco.entity.Responsemsg;
import net.zcscloud.zhuohcun.zeco.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProxy implements GeneralProxy{  //!!Proxy Pattern

    @Autowired
    private UserDao userDao;  //!!Singleton design pattern

    public int getuserrole(int userid) {
        return userDao.getUserRole(userid);
    }

    public AbstractUser findByName(String name){
        return userDao.findByName(name);
    }

    public String lock(String usid, String gottenid) {
        int islocked = -1;
        if(userDao.getIslocked(Integer.parseInt(usid))==1){
            islocked=1;
        }
        if (islocked==1) {
            return "0";
        } else if (islocked==-1) {
            return "-1";
        }else {
            userDao.lock(Integer.parseInt(usid),Integer.parseInt(gottenid));
            return "1";
        }
    }

    public String unlock(String usid, String gottenid) {
        int islocked = -1;
        if(userDao.getIslocked(Integer.parseInt(usid))==1){
            islocked=userDao.getIslocked(Integer.parseInt(usid));
        }
        if (islocked==1) {
            return "0";
        } else if (islocked==-1) {
            return "-1";
        }else {
            userDao.unlock(Integer.parseInt(usid));
            return "1";
        }
    }

    public User getUserbyId(int usid) {
        return userDao.getUserbyId(usid);
    }
}
