package study.servlettospringmvc.mvc.fourth;

import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.mvc.MyView;
import study.servlettospringmvc.mvc.MyViewResolver;
import study.servlettospringmvc.mvc.third.ControllerThird;
import study.servlettospringmvc.mvc.third.MemberFormControllerThird;
import study.servlettospringmvc.mvc.third.MemberListControllerThird;
import study.servlettospringmvc.mvc.third.MemberSaveControllerThird;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="myDispatcherServletFourth",urlPatterns = "/dispatcherservlet/fourth/*")
public class MyDispatcherServletFourth extends HttpServlet {

    private final MemberService memberService;
    private final Map<String, ControllerFourth> urlMap=new HashMap<>();
    private final MyViewResolver myViewResolver;

    public MyDispatcherServletFourth(MemberService memberService,MyViewResolver myViewResolver){
        this.memberService=memberService;
        this.myViewResolver=myViewResolver;

        urlMap.put("/dispatcherservlet/fourth/member-form",new MemberFormControllerFourth());
        urlMap.put("/dispatcherservlet/fourth/save",new MemberSaveControllerFourth(this.memberService));
        urlMap.put("/dispatcherservlet/fourth/members",new MemberListControllerFourth(this.memberService));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ControllerFourth controller=urlMap.get(request.getRequestURI());
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramModel = getParamModel(request);
        Map<String,Object> model=new HashMap<>();

        String viewName = controller.process(paramModel,model);

        MyView myView=myViewResolver.viewResolver(viewName);

        myView.render(model,request, response);
    }

    private Map<String,String> getParamModel(HttpServletRequest request) {
        Map<String,String> model=new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(requestName->model.put(requestName, request.getParameter(requestName)));
        return model;
    }
}
