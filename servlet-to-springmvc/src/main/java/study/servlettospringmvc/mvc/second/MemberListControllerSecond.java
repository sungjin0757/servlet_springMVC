package study.servlettospringmvc.mvc.second;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.mvc.MyView;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class MemberListControllerSecond implements ControllerSecond{

    private final MemberService memberService;

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberService.findAll();
        request.setAttribute("members",members);
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
