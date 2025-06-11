package net.zcscloud.zhuohcun.zeco.dao;
import net.zcscloud.zhuohcun.zeco.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

@Mapper
public interface PlaceDao{
    @Transactional
    @Select("SELECT id,name,address,description FROM place WHERE isdeleted=0")
    List<Place> getPlacesList();

    @Transactional
    @Modifying
    @Update("UPDATE place SET name= :name, description= :description, address= :address WHERE id= :placeid AND isdeleted=0")
    void updatePlaceInfo(String placeid, String name, String description, String address);
}