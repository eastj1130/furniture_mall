package com.east.furns.service.impl;

import com.east.furns.dao.MemberDao;
import com.east.furns.dao.impl.MemberDaoImpl;
import com.east.furns.pojo.Member;
import com.east.furns.service.MemberService;
import com.east.furns.utils.DataUtils;

public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao = new MemberDaoImpl();

    @Override
    public boolean isExistsUsername(String name) {
        return memberDao.querySingleMemberByUsername(name) == null ? false : true;
    }

    @Override
    public boolean registerMember(Member member) {
        return memberDao.insertMember(member) == 1 ? true : false;
    }

    @Override
    public boolean loginMember(Member member) {
        return memberDao.querySingleMemberByUsernamePassword(member) != null ? true : false;
    }

    @Override
    public int queryIdByUserName(String username) {
       return DataUtils.parseInt(memberDao.queryIdByUserName(username), 0);

    }


}
