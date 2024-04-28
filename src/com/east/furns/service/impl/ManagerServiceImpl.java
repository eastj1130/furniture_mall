package com.east.furns.service.impl;

import com.east.furns.dao.ManagerDao;
import com.east.furns.dao.impl.ManagerDaoImpl;
import com.east.furns.pojo.Manager;
import com.east.furns.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao = new ManagerDaoImpl();

    @Override
    public boolean loginManager(Manager manager) {
        return managerDao.queryManagerByUsernamePassword(manager) != null ? true : false;
    }
}
