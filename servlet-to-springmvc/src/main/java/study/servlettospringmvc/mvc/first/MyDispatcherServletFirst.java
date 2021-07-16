package study.servlettospringmvc.mvc.first;

import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerFirst",urlPatterns = "/front-controller/first/*")
public class MyDispatcherServletFirst extends HttpServlet {

    private Map<String,ControllerFirst> urlMap=new HashMap<>();
    private final MemberService memberService;

    public MyDispatcherServletFirst(MemberService memberService) {
        this.memberService=memberService;
        urlMap.put("/front-controller/first/member-form",new MemberFormControllerFirst());
        urlMap.put("/front-controller/first/save",new MemberSaveControllerFirst(this.memberService));
        urlMap.put("/front-controller/first/members",new MemberListControllerFirst(this.memberService));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerFirst controller = urlMap.get(request.getRequestURI());

        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
