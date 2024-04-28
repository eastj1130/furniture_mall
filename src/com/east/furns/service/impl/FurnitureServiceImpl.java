package com.east.furns.service.impl;

import com.east.furns.dao.FurnitureDao;
import com.east.furns.dao.impl.FurnitureDaoImpl;
import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;
import com.east.furns.service.FurnitureService;
import com.east.furns.utils.DataUtils;

import java.util.List;

public class FurnitureServiceImpl implements FurnitureService {
    FurnitureDao furnitureDao = new FurnitureDaoImpl();

    @Override
    public List<Furniture> queryAllFurniture() {
        return furnitureDao.queryAllFurniture();
    }

    @Override
    public boolean addFurniture(Furniture furniture) {
        return furnitureDao.addFurniture(furniture) == 1 ? true : false;
    }

    @Override
    public boolean deleteFurnitureById(int id) {
        //        http://localhost:8080/mall/manager/furnitureServlet?action=deleteFurniture&id=1

        return furnitureDao.deleteFurnitureById(id) == 1 ? true : false;
    }

    @Override
    public Furniture querySingleFurnitureById(int id) {
        return furnitureDao.querySingleFurnitureById(id);
    }

    @Override
    public boolean updateSingleFurnitureById(Furniture furniture, int id) {
        return furnitureDao.updateSingleFurnitureById(furniture, id) == 1 ? true : false;
    }

    @Override
    public Page<Furniture> singlePage(int pageNo, int pageSize) {
        int start = (pageNo - 1) * pageSize;
        int totalItems = furnitureDao.totalFurniture();
        List<Furniture> furnitures = furnitureDao.queryFurnitureItemsByPage(start, pageSize);
        Page<Furniture> furniturePage = new Page<>();
        int totalPage = (int)(Math.ceil((double) totalItems / pageSize));
        furniturePage.setPageNo(pageNo);
        furniturePage.setPageSize(pageSize);
        furniturePage.setItems(furnitures);
        furniturePage.setTotalItems(totalItems);
        furniturePage.setTotalPage(totalPage);
        return furniturePage;
    }

    @Override
    public Page<Furniture> queryFurnitureByName(String name,int pageNo,int pageSize) {

        int start = (pageNo - 1) * pageSize;
        int totalItems = furnitureDao.totalSearchFurniture(name);
        List<Furniture> furnitures = furnitureDao.queryFurnitureByName(name,start, pageSize);
        Page<Furniture> furniturePage = new Page<>();
        int totalPage = (int)(Math.ceil((double) totalItems / pageSize));
        furniturePage.setPageNo(pageNo);
        furniturePage.setPageSize(pageSize);
        furniturePage.setItems(furnitures);
        furniturePage.setTotalItems(totalItems);
        furniturePage.setTotalPage(totalPage);
        return furniturePage;
    }
}
