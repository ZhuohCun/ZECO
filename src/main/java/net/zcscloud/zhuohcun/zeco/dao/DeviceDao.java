package net.zcscloud.zhuohcun.zeco.dao;

import net.zcscloud.zhuohcun.zeco.entity.AbstractDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface DeviceDao {

    @Transactional
    @Update("UPDATE `device` SET `cvalue`=#{value},`ccondition`=#{condition},`color`=#{color} WHERE `id`=#{devid} AND `isdeleted`=0")
    void updateDeviceValue(@Param("devid") int devid,
                           @Param("value") String value,
                           @Param("color") String color,
                           @Param("condition") String condition);

    @Select("SELECT `id$user` FROM `device` WHERE `id`=#{devid} AND `isdeleted`=0 LIMIT 1")
    int getusidbydevid(@Param("devid") int devid);

    @Select("SELECT `max_value` FROM `device` WHERE `id`=#{devid} AND `isdeleted`=0")
    float getmaxvaluebydevid(@Param("devid") int devid);

    @Select("SELECT `id`,`name`,`type`,`unit`,`cvalue`,`ccondition`,`color`,`id$place` FROM `device` WHERE `id$place`=#{placeid} AND `isdeleted`=0")
    List<AbstractDevice> getdevicesbyplaceid(@Param("placeid") int placeid);

    @Transactional
    @Update("UPDATE `device` SET `name`=#{name},`type`=#{type},`unit`=#{unit},`max_value`=#{maxvalue},`physicalid`=#{physicalid},`placeid`=#{placeid} WHERE `id`=#{devid} AND `isdeleted`=0")
    void updateDeviceSpecs(@Param("devid") int devid,
                           @Param("name") String name,
                           @Param("type") int type,
                           @Param("unit") String unit,
                           @Param("maxvalue") float maxvalue,
                           @Param("physicalid") String physicalid,
                           @Param("placeid") int placeid);

    @Transactional
    @Update("UPDATE `device` SET `isdeleted`=1,`deletedby`=#{usid} WHERE `id`=#{devid} AND `isdeleted`=0")
    void deleteDevice(@Param("devid") int devid,
                      @Param("usid") int usid);

    @Select("SELECT * FROM `device` WHERE `id`=#{devid} AND `isdeleted`=0 LIMIT 1")
    AbstractDevice getDevicebyId(@Param("devid") int devid);

    @Select("SELECT `cvalue` FROM `device` WHERE `id`=#{devid} AND `isdeleted`=0")
    int getUpdate(@Param("devid") int devid);

}
