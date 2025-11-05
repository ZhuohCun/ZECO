package net.zcscloud.zhuohcun.zeco.dao;

import net.zcscloud.zhuohcun.zeco.entity.AbstractUser;
import net.zcscloud.zhuohcun.zeco.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.Modifying;


import javax.transaction.Transactional;

@Mapper
public interface UserDao{
    AbstractUser findByName(String name);

    @Transactional
    @Select("SELECT role FROM user where id= :usid and isdeleted=0 limit 1") //Demete Law
    int getUserRole(int usid);

    @Transactional
    @Select("SELECT islocked FROM user WHERE id= :usid and isdeleted=0")
    int getIslocked(int usid);

    @Transactional
    @Modifying
    @Update("UPDATE user SET islocked=1, lockedby= :gottenid WHERE id= :usid and isdeleted=0")
    void lock(int usid, int gottenid);

    @Transactional
    @Modifying
    @Update("UPDATE user SET islocked=0, lockedby=0 WHERE id= :usid and isdeleted=0")
    void unlock(int usid, int gottenid);

    @Transactional
    @Select("SELECT * FROM user WHERE id= :usid AND isdeleted=0 limit1")
    User getUserbyId(int usid);
}