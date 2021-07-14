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
import java.util.List;

@WebServlet(name = "memberListControllerFirst",urlPatterns = "/mvc/first/members")
@RequiredArgsConstructor
public class MemberListControllerFirst extends HttpServlet {

    private final MemberService memberService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberService.findAll();

        request.setAttribute("members",members);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/members.jsp");
        dispatcher.forward(request,response);

    }
}
