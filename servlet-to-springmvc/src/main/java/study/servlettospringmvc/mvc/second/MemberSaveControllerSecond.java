package study.servlettospringmvc.mvc.second;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.mvc.MyView;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class MemberSaveControllerSecond implements ControllerSecond{

    private final MemberService memberService;

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member=new Member();
        member.saveMember(username,age);

        memberService.join(member);
        
        request.setAttribute("member",member);

        return new MyView("/WEB-INF/views/save-success.jsp");
    }
}
