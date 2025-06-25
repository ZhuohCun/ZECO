package net.zcscloud.zhuohcun.zeco.dao;

import net.zcscloud.zhuohcun.zeco.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import javax.transaction.Transactional;
import java.util.List;

@Mapper
public interface PlaceDao{

    @Select("SELECT `id`,`name`,`address`,`description` FROM `place` WHERE `isdeleted`=0")
    List<Place> getPlacesList();

    @Transactional
    @Update("UPDATE `place` SET `name`=#{name},`description`=#{description},`address`=#{address} WHERE `id`=#{placeid} AND `isdeleted`=0")
    void updatePlaceInfo(@Param("placeid") String placeid, @Param("name") String name, @Param("description") String description, @Param("address") String address);

}