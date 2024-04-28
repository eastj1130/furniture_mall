package com.east.furns.test;

import com.east.furns.dao.ManagerDao;
import com.east.furns.dao.impl.ManagerDaoImpl;
import com.east.furns.pojo.Manager;
import org.junit.Test;

public class TestManagerDaoImpl {
    ManagerDao managerDao =  new ManagerDaoImpl();
    @Test
    public void test(){
        Manager manager = new Manager(null, "manager", "manager");
        Manager manager1 = managerDao.queryManagerByUsernamePassword(manager);
        System.out.println(manager1);


    }
}
