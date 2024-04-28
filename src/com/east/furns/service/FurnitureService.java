package com.east.furns.service;

import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;

import java.util.List;

public interface FurnitureService {
    List<Furniture> queryAllFurniture();
    boolean addFurniture(Furniture furniture);
    boolean deleteFurnitureById(int id);
    Furniture querySingleFurnitureById(int id);

    boolean updateSingleFurnitureById(Furniture furniture,int id);

    Page<Furniture> singlePage(int pageNo,int pageSize);
    Page<Furniture> queryFurnitureByName(String name,int pageNo,int pageSize);
}
