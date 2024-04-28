package com.east.furns.dao;

import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;

import java.util.List;

public interface FurnitureDao {
    List<Furniture> queryAllFurniture();
    int addFurniture(Furniture furniture);
    int deleteFurnitureById(int id);
    Furniture querySingleFurnitureById(int id);
    int updateSingleFurnitureById(Furniture furniture, int id);
    List<Furniture> queryFurnitureItemsByPage(int begin,int PageSize);

    int totalFurniture();
    int totalSearchFurniture(String name);
   List<Furniture> queryFurnitureByName(String name,int begin,int PageSize);
}
