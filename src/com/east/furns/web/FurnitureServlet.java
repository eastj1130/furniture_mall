package com.east.furns.web;

import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Page;
import com.east.furns.service.FurnitureService;
import com.east.furns.service.impl.FurnitureServiceImpl;
import com.east.furns.utils.DataUtils;
import com.east.furns.utils.DateUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
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
        DiskFileItemFactory.Builder builder = new DiskFileItemFactory.Builder();
        DiskFileItemFactory diskFileItemFactory = builder.get();
        JakartaServletDiskFileUpload fileUpload = new JakartaServletDiskFileUpload(diskFileItemFactory);
        List<DiskFileItem> itemList = fileUpload.parseRequest(request);
        ServletContext servletContext = request.getServletContext();
        int pageNo = 1;
        String name = "";
        String maker = "";
        BigDecimal price = null;
        int sales = 0;
        int stock = 0;
        String fileFullPath = "";
        fileUpload.setHeaderCharset(Charset.forName("utf-8"));
        for (DiskFileItem diskFileItem : itemList) {
            if (!diskFileItem.isFormField()) {
                String fileName = new Date().getTime() + diskFileItem.getName();
                String fileDir = servletContext.getRealPath("/") + "upload/" + DateUtil.getDate();
                File file = new File(fileDir);
                if (!file.exists()) {
                    file.mkdirs();
                }
                fileFullPath = fileDir + "/" + fileName;
                File file1 = new File(fileFullPath);
                System.out.println(fileFullPath);
                diskFileItem.write(file1.toPath());
            } else {
                if ("pageNo".equals(diskFileItem.getFieldName())) {
                    pageNo = DataUtils.parseInt(diskFileItem.getString(Charset.forName("utf-8")), 1);
                } else if ("name".equals(diskFileItem.getFieldName())) {
                    name = diskFileItem.getString(Charset.forName("utf-8"));
                } else if ("maker".equals(diskFileItem.getFieldName())) {
                    maker = diskFileItem.getString(Charset.forName("utf-8"));
                } else if ("price".equals(diskFileItem.getFieldName())) {
                    price = new BigDecimal(diskFileItem.getString(Charset.forName("utf-8")));
                } else if ("sales".equals(diskFileItem.getFieldName())) {
                    sales = DataUtils.parseInt(diskFileItem.getString(Charset.forName("utf-8")), 0);
                } else if ("stock".equals(diskFileItem.getFieldName())) {
                    stock = DataUtils.parseInt(diskFileItem.getString(Charset.forName("utf-8")), 0);
                }
            }
        }
        // 处理路径字符串
        String[] split = fileFullPath.split("/");
        String[] strings = Arrays.copyOfRange(split, 10, split.length);
        StringBuilder stringBuilder = new StringBuilder(fileFullPath);
        for (String string : strings) {
            stringBuilder.append(string + "/");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("/"));
        // 处理字符串完毕
        Furniture furniture = new Furniture(null, name, maker, price, sales, stock, stringBuilder.toString());
        furnitureService.addFurniture(furniture);
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
