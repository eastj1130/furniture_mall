package com.east.furns.dao.impl;

import com.east.furns.dao.BasicDao;
import com.east.furns.dao.MemberDao;
import com.east.furns.pojo.Member;

public class MemberDaoImpl extends BasicDao<Member> implements MemberDao {
    @Override
    public Member querySingleMemberByUsername(String username) {
        return querySingle("select `id`,`username`,`password`,`email` from `member` where username = ?",
                Member.class, username);
    }

    @Override
    public int insertMember(Member member) {
        return update("insert into member(username,password,email) values(?,?,?)",
                member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public Member querySingleMemberByUsernamePassword(Member member) {
        return querySingle("select `id`,`username`,`password`,`email` from `member` where username = ? and password = ?"
                , Member.class, member.getUsername(), member.getPassword());
    }

    @Override
    public String queryIdByUserName(String username) {
        String sql = "select `id` from `member` where username=?";
        return queryScalar(sql, username).toString();
    }
}
