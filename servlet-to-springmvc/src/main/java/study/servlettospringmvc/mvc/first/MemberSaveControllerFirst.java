package study.servlettospringmvc.mvc.first;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="memberSaveControllerFirst",urlPatterns = "/mvc/first/save")
@RequiredArgsConstructor
public class MemberSaveControllerFirst extends HttpServlet {
    private final MemberService memberService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member=new Member();
        member.saveMember(username,age);

        memberService.join(member);

        request.setAttribute("member",member);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/save-success.jsp");
        dispatcher.forward(request,response);
    }
}
