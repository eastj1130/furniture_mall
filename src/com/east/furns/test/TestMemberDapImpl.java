package com.east.furns.test;

import com.east.furns.dao.MemberDao;
import com.east.furns.dao.impl.MemberDaoImpl;
import com.east.furns.pojo.Member;
import com.east.furns.utils.JdbcUtilsByDruid;
import org.junit.Test;

import java.awt.*;
import java.security.PublicKey;
import java.sql.Connection;

public class TestMemberDapImpl {
    @Test
    public void test(){
        MemberDao memberDao = new MemberDaoImpl();
//        Member adminuser = memberDao.querySingleMemberByUsername("adminuser");
//        System.out.println(adminuser);
//
//        Member member = new Member(10, "jddddddd", "dfadfsad", "afsd@dsd.com");
//        int b = memberDao.insertMember(member);
//        System.out.println(b);
//        Member member = new Member(null, "adminuser", "adminuser", null);
//        Member memberDB = memberDao.querySingleMemberByUsernamePassword(member);
//        System.out.println(memberDB);


        String s = memberDao.queryIdByUserName("adminuser");
        System.out.println(s);
    }
}
