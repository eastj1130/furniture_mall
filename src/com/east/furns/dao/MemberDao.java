package com.east.furns.dao;

import com.east.furns.pojo.Member;

public interface MemberDao {
    Member querySingleMemberByUsername(String username);
    int insertMember(Member member);
    Member querySingleMemberByUsernamePassword(Member member);

    String queryIdByUserName(String username);
}
