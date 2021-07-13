package study.servlettospringmvc.onlyservlet.members;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.respository.MemberRepository;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet",urlPatterns = "/onlyservlet/members")
@RequiredArgsConstructor
public class MemberListServlet extends HttpServlet {

    private final MemberService memberService;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberService.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();

        writer.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/index.html\">메인</a>\n"+
                "<table>\n"+
                "<tbody>\n"+
                " <thead>\n"+
                " <th>id</th>\n"+
                " <th>username</th>\n"+
                " <th>age</th>\n"+
                " </thead>\n"+
                " <tbody>\n");
        for (Member member : members) {
            writer.write(" <tr>");
            writer.write(" <td>" + member.getId() + "</td>");
            writer.write(" <td>" + member.getUsername() + "</td>");
            writer.write(" <td>" + member.getAge() + "</td>");
            writer.write(" </tr>");
        }
        writer.write(" </tbody>\n"+
                "</table>\n"+
                "</body>\n"+
                "</htm;>");
    }




}
