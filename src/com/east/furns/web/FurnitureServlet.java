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
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/manager/furnitureServlet")
public class FurnitureServlet extends BasicServlet {
    FurnitureService furnitureService = new FurnitureServiceImpl();

    public void showFurnitureList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<Furniture> furnitures = furnitureService.queryAllFurniture();
        request.setAttribute("furnitureList", furnitures);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);


    }

    public void singlePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE_DEFAULT);
        Page<Furniture> furniturePage = furnitureService.singlePage(pageNo, pageSize);
        request.setAttribute("furniturePage", furniturePage);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }


    public void addFurniture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        Furniture furniture = DataUtils.copyParametersToBean(new Furniture(), request.getParameterMap());
        furnitureService.addFurniture(furniture);
//        response.sendRedirect(request.getContextPath() + "/manager/furnitureServlet?action=showFurnitureList");
        response.sendRedirect(request.getContextPath() +
                "/manager/furnitureServlet?action=singlePage&pageSize=3&pageNo=" + pageNo);
    }

    public void deleteFurniture(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer id = DataUtils.parseInt(request.getParameter("id"), 0);
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        furnitureService.deleteFurnitureById(id);
//        response.sendRedirect(request.getContextPath() + "/manager/furnitureServlet?action=showFurnitureList");
        response.sendRedirect(request.getContextPath() +
                "/manager/furnitureServlet?action=singlePage&pageSize=3&pageNo=" + pageNo);

    }

    protected void updateFurniturePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = DataUtils.parseInt(request.getParameter("id"), 0);
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        Furniture furniture = furnitureService.querySingleFurnitureById(id);
        request.setAttribute("furniture", furniture);
        request.setAttribute("pageNo", pageNo);
        request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);

    }

    protected void updateFurniture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furniture furniture = new Furniture();
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        DataUtils.copyParametersToBean(furniture, request.getParameterMap());
        furnitureService.updateSingleFurnitureById(furniture, id);
        response.sendRedirect(request.getContextPath() +
                "/manager/furnitureServlet?action=singlePage&pageSize=3&pageNo=" + pageNo);
    }


}
