package study.servlettospringmvc.mvc.second;

import study.servlettospringmvc.mvc.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerSecond implements ControllerSecond{

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/member-form.jsp");
    }
}
