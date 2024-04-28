package com.east.furns.dao;

import com.east.furns.pojo.Manager;

public interface ManagerDao {
    Manager queryManagerByUsernamePassword(Manager manager);
}
