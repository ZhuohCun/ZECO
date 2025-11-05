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
    private UserDao userDao;  //!!Single Existence Pattern

    public int getuserrole(int userid) {
        return userDao.getUserRole(userid);
    }

    public AbstractUser findByName(String name){
        return userDao.findByName(name);
    }

    public Responsemsg lock(String usid, String gottenid) {
        int islocked = -1;
        if(userDao.getIslocked(Integer.parseInt(usid))==1){
            islocked=userDao.getIslocked(Integer.parseInt(usid));
        }
        if (islocked==0) {
            return Responsemsg.error("0");
        } else if (islocked==-1) {
            return Responsemsg.error("-1");
        }else {
            userDao.lock(Integer.parseInt(usid),Integer.parseInt(gottenid));
            return Responsemsg.success("1");
        }
    }

    public Responsemsg unlock(String usid, String gottenid) {
        int islocked = -1;
        if(userDao.getIslocked(Integer.parseInt(usid))==1){
            islocked=userDao.getIslocked(Integer.parseInt(usid));
        }
        if (islocked==1) {
            return Responsemsg.error("0");
        } else if (islocked==-1) {
            return Responsemsg.error("-1");
        }else {
            userDao.unlock(Integer.parseInt(usid),Integer.parseInt(gottenid));
            return Responsemsg.success("1");
        }
    }

    public User getUserbyId(int usid) {
        return userDao.getUserbyId(usid);
    }
}
