package com.east.furns.service;

import com.east.furns.pojo.Member;

public interface MemberService {
    boolean isExistsUsername(String username);
    boolean registerMember(Member member);

    boolean loginMember(Member member);

    int queryIdByUserName(String username);
}
