package com.east.furns.dao.impl;

import com.east.furns.dao.BasicDao;
import com.east.furns.dao.FurnitureDao;
import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;

import java.util.List;

public class FurnitureDaoImpl extends BasicDao<Furniture> implements FurnitureDao {
    @Override
    public List<Furniture> queryAllFurniture() {
        String sql = "select id,`name`,maker,price,sales,stock,img_path as imgPath from furniture";
        return queryMulti(sql, Furniture.class);
    }

    @Override
    public int addFurniture(Furniture furniture) {
        String sql = "insert into furniture(name,maker,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql, furniture.getName(),
                furniture.getMaker(),
                furniture.getPrice(),
                furniture.getSales(),
                furniture.getStock(),
                furniture.getImgPath());
    }

    @Override
    public int deleteFurnitureById(int id) {

        String sql = "delete from furniture where id = ?";
        return update(sql, id);
    }

    @Override
    public Furniture querySingleFurnitureById(int id) {
        String sql = "select id,name,maker,price,sales,stock,img_path from furniture where id = ?";
        return querySingle(sql, Furniture.class, id);
    }


    @Override
    public int updateSingleFurnitureById(Furniture furniture, int id) {
        String sql = "update furniture set name = ?,maker = ?, price = ?, sales = ? ,stock = ? where id = ?";
        return update(sql,
                furniture.getName(),
                furniture.getMaker(),
                furniture.getPrice(),
                furniture.getSales(),
                furniture.getStock(),
                id);

    }

    @Override
    public List<Furniture> queryFurnitureItemsByPage(int begin, int pageSize) {
        String sql = "select id,name,maker,price,sales,stock,img_path as imgPath from furniture limit ?,?";
        return queryMulti(sql, Furniture.class, begin, pageSize);
    }

    @Override
    public int totalFurniture() {
        String sql = "select count(*) from furniture";
        Object number = queryScalar(sql);
        return ((Number) number).intValue();
    }

    @Override
    public int totalSearchFurniture(String name) {
        String sql = "select count(*) from (select id from furniture where name LIKE ?) as countTable";
        String fullName = "%"+name +"%";
        Object number = queryScalar(sql,fullName);
        return ((Number) number).intValue();
    }

    @Override
    public List<Furniture> queryFurnitureByName(String name,int begin,int pageSize) {
        String sql = "select id,name,maker,price,sales,stock,img_path as imgPath from furniture where name like ? limit ?,?";
        String fullName = "%"+name +"%";
        return queryMulti(sql,Furniture.class,fullName,begin,pageSize);
    }
}
