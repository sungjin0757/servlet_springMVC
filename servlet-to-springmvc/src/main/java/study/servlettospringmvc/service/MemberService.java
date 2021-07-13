package study.servlettospringmvc.service;

import study.servlettospringmvc.domain.Member;

import java.util.List;

public interface MemberService {
    public Long join(Member member);
    public Member findOne(Long memberId);
    public List<Member> findAll();
}
