package com.east.furns.test;

import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;
import com.east.furns.service.FurnitureService;
import com.east.furns.service.impl.FurnitureServiceImpl;
import com.east.furns.utils.DataUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class TestFurnitureServiceImpl {
    FurnitureService furnitureService = new FurnitureServiceImpl();
    @Test
    public void test(){
//        List<Furniture> furnitures = furnitureService.queryAllFurniture();
//        for (Furniture furniture : furnitures) {
//            System.out.println(furniture);
//        }

        /**
         * imgPath: assets/images/product-image/default.jpg
         * name: Name
         * maker: 蚂蚁家居
         * price: 60.00
         * sales: 100
         * stock: 80
         */

        Furniture furniture = new Furniture(null, "sfsd", "fsdf", new BigDecimal("7979"), 424, 4234, "assets/images/product-image/default.jpg");
        boolean b = furnitureService.addFurniture(furniture);
        System.out.println(b);

        //        http://localhost:8080/mall/manager/furnitureServlet?action=deleteFurniture&id=1

//        boolean b = furnitureService.deleteFurnitureById(33);
//        System.out.println(b);
//        Furniture furniture = furnitureService.querySingleFurnitureById(1);
////        System.out.println(furniture);
//
//        System.out.println(DataUtils.parseInt("42fds34",0));
//
//        Furniture furniture =
//                new Furniture(null, "dfs", "fsf", new BigDecimal("79"), 43, 42, null);
//        boolean b = furnitureService.updateSingleFurnitureById(furniture, 36);
//        Page<Furniture> furniturePage = furnitureService.singlePage(1, 3);
//        System.out.println(furniturePage);

//        Page<Furniture> furniturePage = furnitureService.queryFurnitureByName("Name", 1, 4);
//        System.out.println(furniturePage);
//        for (Furniture item : furniturePage.getItems()) {
//            System.out.println(item);
//        }
    }
}
