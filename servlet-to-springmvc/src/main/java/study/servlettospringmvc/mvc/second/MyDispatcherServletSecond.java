package study.servlettospringmvc.mvc.second;

import study.servlettospringmvc.mvc.first.ControllerFirst;
import study.servlettospringmvc.mvc.first.MemberFormControllerFirst;
import study.servlettospringmvc.mvc.first.MemberListControllerFirst;
import study.servlettospringmvc.mvc.first.MemberSaveControllerFirst;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="myDispatcherServletSecond",urlPatterns = "/dispatcherservlet/second/*")
public class MyDispatcherServletSecond extends HttpServlet {

    private final MemberService memberService;

    private Map<String, ControllerSecond> urlMap=new HashMap<>();

    public MyDispatcherServletSecond(MemberService memberService) {
        this.memberService=memberService;
        urlMap.put("/dispatcherservlet/second/member-form",new MemberFormControllerSecond());
        urlMap.put("/dispatcherservlet/second/save",new MemberSaveControllerSecond(this.memberService));
        urlMap.put("/dispatcherservlet/second/members",new MemberListControllerSecond(this.memberService));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerSecond controller=urlMap.get(request.getRequestURI());

        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response).render(request, response);
    }
}
