package net.zcscloud.zhuohcun.zeco.dao;

import net.zcscloud.zhuohcun.zeco.entity.AbstractDevice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

@Mapper
public interface DeviceDao{

    @Modifying
    @Transactional
    @Update("UPDATE Device SET cvalue= :value , ccondition=  :condition , color= :color where id= :devid and isdeleted=0")
    void updateDeviceValue(int devid, String value, String color,String condition);

    @Modifying
    @Transactional
    @Insert("SELECT id$user FROM device WHERE id= :devid and isdeleted=0 limit 1")  //!!Demete Law
    int getusidbydevid(int devid);

    @Transactional
    @Select("SELECT maxvalue FROM device WHERE id= :devid and isdeleted=0")  //!!Demete Law  /only provide necessary information
    float getmaxvaluebydevid(int devid);

    @Transactional
    @Select("SELECT type,unit,cvalue,ccondition,color,id$place FROM device where id$place= :placeid and isdeleted=0")  //!!Demete Law
    List<AbstractDevice> getdevicesbyplaceid(int placeid);

    @Transactional
    @Modifying
    @Update("UPDATE device SET name= :name, type= :type, unit= :unit, maxvalue= :maxvalue, physicalid= :physicalid, placeid= :placeid WHERE id= :devid and isdeleted=0")
    void updateDeviceSpecs(int devid, String name, int type, String unit, String maxvalue, String physicalid, int placeid);

    @Transactional
    @Modifying
    @Update("UPDATE device SET isdeleted=1, deletedby= :usid WHERE id= :devid and isdeleted=0")
    void deleteDevice(String devid, int usid);

    @Transactional
    @Select("SELECT * FROM device WHERE id= :devid AND isdeleted=0 limit1")
    AbstractDevice getDevicebyId(int devid);
}