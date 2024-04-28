package com.east.furns.dao.impl;

import com.east.furns.dao.BasicDao;
import com.east.furns.dao.ManagerDao;
import com.east.furns.pojo.Manager;
import com.east.furns.web.BasicServlet;
import com.oracle.wls.shaded.org.apache.xalan.processor.XSLTElementDef;

public class ManagerDaoImpl extends BasicDao<Manager> implements ManagerDao {
    @Override
    public Manager queryManagerByUsernamePassword(Manager manager) {
        String sql = "select username,password from manager where username = ? and password = ?";
        return querySingle(sql,Manager.class,manager.getUsername(),manager.getPassword());
    }
}
