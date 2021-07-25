package study.servlettospringmvc.mvc.complete;


import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.mvc.MyView;
import study.servlettospringmvc.mvc.MyViewResolver;
import study.servlettospringmvc.mvc.complete.adapter.ControllerFourthHandlerAdapter;
import study.servlettospringmvc.mvc.complete.adapter.ControllerThirdHandlerAdapter;
import study.servlettospringmvc.mvc.fourth.MemberFormControllerFourth;
import study.servlettospringmvc.mvc.fourth.MemberListControllerFourth;
import study.servlettospringmvc.mvc.fourth.MemberSaveControllerFourth;
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
import java.util.*;

@WebServlet(name="myDispatcherServletComplete",urlPatterns = "/dispatcherservlet/complete/*")
public class MyDispatcherServletComplete extends HttpServlet {

    private final MemberService memberService;
    private final MyViewResolver myViewResolver;
    private final Map<String,Object> handlerMappingMap=new HashMap<>();
    private final List<MyHandlerAdapter> myHandlerAdapters=new ArrayList<>();

    public MyDispatcherServletComplete(MemberService memberService,MyViewResolver viewResolver){
        this.memberService=memberService;
        this.myViewResolver=viewResolver;

        handlerMappingMap.put("/dispatcherservlet/complete/third/member-form",new MemberFormControllerThird());
        handlerMappingMap.put("/dispatcherservlet/complete/third/save",new MemberSaveControllerThird(this.memberService));
        handlerMappingMap.put("/dispatcherservlet/complete/third/members",new MemberListControllerThird(this.memberService));

        handlerMappingMap.put("/dispatcherservlet/complete/fourth/member-form",new MemberFormControllerFourth());
        handlerMappingMap.put("/dispatcherservlet/complete/fourth/save",new MemberSaveControllerFourth(this.memberService));
        handlerMappingMap.put("/dispatcherservlet/complete/fourth/members",new MemberListControllerFourth(this.memberService));

        myHandlerAdapters.add(new ControllerFourthHandlerAdapter());
        myHandlerAdapters.add(new ControllerThirdHandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = handlerMappingMap.get(request.getRequestURI());

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter=getAdapter(handler);

        MyModel myModel = adapter.handle(request, response, handler);
        MyView myView = myViewResolver.viewResolver(myModel.getViewPath());

        myView.render(myModel.getModel(),request,response);

    }

    private MyHandlerAdapter getAdapter(Object handler){
        Optional<MyHandlerAdapter> adapter = myHandlerAdapters.stream().filter(a -> a.supports(handler)).findFirst();
        if(adapter.isEmpty())
            throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다"+handler);
        else
            return adapter.get();

    }
}
