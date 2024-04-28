package com.east.furns.web;

import com.east.furns.utils.DateUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;
import org.apache.commons.fileupload2.jakarta.JakartaServletRequestContext;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/fileUploadServlet")
public class FIleUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory.Builder builder = new DiskFileItemFactory.Builder();
        DiskFileItemFactory diskFileItemFactory = builder.get();
        JakartaServletDiskFileUpload fileUpload = new JakartaServletDiskFileUpload(diskFileItemFactory);
        List<DiskFileItem> itemList = fileUpload.parseRequest(request);
        System.out.println("niao");
        ServletContext servletContext = request.getServletContext();
        for (DiskFileItem diskFileItem : itemList) {
            if (!diskFileItem.isFormField()){
                String fileName = new Date().getTime() + diskFileItem.getName();
                String fileDir = servletContext.getRealPath("/")+"upload/"+ DateUtil.getDate();
                File file = new File(fileDir);
                if (!file.exists()){
                    file.mkdirs();
                }
                String fileFullPath  = fileDir+"/"+fileName;
                File file1 = new File(fileFullPath);
                System.out.println(fileFullPath);
                diskFileItem.write(file1.toPath());
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
