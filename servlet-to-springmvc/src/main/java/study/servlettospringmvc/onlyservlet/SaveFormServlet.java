package study.servlettospringmvc.onlyservlet;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.respository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="saveFormServlet",urlPatterns = "/onlyservlet/member-form")
@RequiredArgsConstructor
public class SaveFormServlet extends HttpServlet {

    private final MemberRepository memberRepository;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/onlyservlet/save\" method=\"post\">\n" +
                "<label for=\"username\">이름</label>"+
                " <input type=\"text\" name=\"username\" id=\"username\" value=\"이름\"/>\n" +
                "<label for=\"age\">나이</label>"+
                " <input type=\"text\" name=\"age\" value=\"나이\" id=\"age\"/>\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }

}
