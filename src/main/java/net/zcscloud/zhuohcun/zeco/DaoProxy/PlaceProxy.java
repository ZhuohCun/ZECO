package net.zcscloud.zhuohcun.zeco.DaoProxy;

import net.zcscloud.zhuohcun.zeco.dao.PlaceDao;
import net.zcscloud.zhuohcun.zeco.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaceProxy  implements GeneralProxy{  //!!Proxy Pattern
    @Autowired
    PlaceDao placeDao;
    public List<Place> getPlacesList() {
        return placeDao.getPlacesList();
    }

    public void updatePlaceInfo(String placeid, String name, String description, String address) {
        placeDao.updatePlaceInfo(placeid,name,description,address);
    }
}
