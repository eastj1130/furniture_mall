package com.east.furns.web;

import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;
import com.east.furns.service.FurnitureService;
import com.east.furns.service.impl.FurnitureServiceImpl;
import com.east.furns.utils.DataUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/customerFurniture")
public class CustomerFurnitureServlet extends BasicServlet{
   FurnitureService furnitureService =  new FurnitureServiceImpl();
    protected void furniturePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), 1);
        Page<Furniture> furniturePage = furnitureService.singlePage(pageNo, pageSize);
        request.setAttribute("action","furniturePage");
        request.setAttribute("furniturePage",furniturePage);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request,response);
    }

    protected void searchFurnitureByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if (null == name)
            name = "";
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE_DEFAULT);

        Page<Furniture> furniturePage = furnitureService.queryFurnitureByName(name,pageNo, pageSize);
        StringBuilder stringBuilder = new StringBuilder("customer/customerFurniture?action=searchFurnitureByName&");
        StringBuilder append = stringBuilder.append("name=" + name);
        furniturePage.setUrl(append.toString());
        request.setAttribute("furniturePage",furniturePage);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request,response);

    }
}
