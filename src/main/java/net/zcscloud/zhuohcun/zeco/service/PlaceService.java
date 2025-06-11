package net.zcscloud.zhuohcun.zeco.service;

import net.zcscloud.zhuohcun.zeco.DaoProxy.PlaceProxy;
import net.zcscloud.zhuohcun.zeco.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class PlaceService{
    @Autowired
    PlaceProxy placeProxy;
    public List<Place> getPlacesList() throws IOException {
        return placeProxy.getPlacesList();
    }

    public void updatePlaceInfo(String placeid, String name, String description, String address) throws IOException {
        placeProxy.updatePlaceInfo(placeid,name,description,address);
    }
}
