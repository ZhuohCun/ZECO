package net.zcscloud.zhuohcun.zeco.dao;

import net.zcscloud.zhuohcun.zeco.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.transaction.Transactional;

@Mapper
public interface UserDao{

    @Transactional
    @Select("SELECT `role` FROM `user` WHERE `id`=#{usid} AND `isdeleted`=0 LIMIT 1")
    int getUserRole(@Param("usid") int usid);

    @Transactional
    @Select("SELECT `islocked` FROM `user` WHERE `id`=#{usid} AND `isdeleted`=0")
    int getIslocked(@Param("usid") int usid);

    @Transactional
    @Update("UPDATE `user` SET `islocked`=1,`lockedby`=#{gottenid} WHERE `id`=#{usid} AND `isdeleted`=0")
    void lock(@Param("usid") int usid, @Param("gottenid") int gottenid);

    @Transactional
    @Update("UPDATE `user` SET `islocked`=0,`lockedby`=0 WHERE `id`=#{usid} AND `isdeleted`=0")
    void unlock(@Param("usid") int usid);

    @Transactional
    @Select("SELECT * FROM `user` WHERE `id`=#{usid} AND `isdeleted`=0 LIMIT 1")
    User getUserbyId(@Param("usid") int usid);

    @Transactional
    @Select("SELECT * FROM `user` WHERE `username`=#{name} AND `isdeleted`=0 AND `islocked`=0 LIMIT 1")
    User findByName(@Param("name") String name);

}
