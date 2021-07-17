package study.servlettospringmvc.mvc.third;

import lombok.extern.slf4j.Slf4j;
import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.mvc.MyView;
import study.servlettospringmvc.mvc.MyViewResolver;
import study.servlettospringmvc.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="myDispatcherServletThird",urlPatterns = "/dispatcherservlet/third/*")
@Slf4j
public class MyDispatcherServletThird extends HttpServlet {

    private final MemberService memberService;
    private final Map<String,ControllerThird> urlMap=new HashMap<>();
    private final MyViewResolver myViewResolver;

    public MyDispatcherServletThird(MemberService memberService,MyViewResolver myViewResolver){
        this.memberService=memberService;
        this.myViewResolver=myViewResolver;

        urlMap.put("/dispatcherservlet/third/member-form",new MemberFormControllerThird());
        urlMap.put("/dispatcherservlet/third/save",new MemberSaveControllerThird(this.memberService));
        urlMap.put("/dispatcherservlet/third/members",new MemberListControllerThird(this.memberService));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ControllerThird controller=urlMap.get(request.getRequestURI());
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramModel = getParamModel(request);
        MyModel model = controller.process(paramModel);

        MyView myView=myViewResolver.viewResolver(model.getViewPath());

        myView.render(model.getModel(),request, response);
    }

    private Map<String,String> getParamModel(HttpServletRequest request) {
        Map<String,String> model=new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(requestName->model.put(requestName, request.getParameter(requestName)));
        return model;
    }
}
