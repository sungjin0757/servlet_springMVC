package study.servlettospringmvc.mvc.complete.adapter;

import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.mvc.complete.MyHandlerAdapter;
import study.servlettospringmvc.mvc.fourth.ControllerFourth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerFourthHandlerAdapter implements MyHandlerAdapter  {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerFourth);
    }

    @Override
    public MyModel handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerFourth controller=(ControllerFourth)handler;

        Map<String,String> paramMap=getParamModel(request);
        Map<String,Object> model=new HashMap<>();

        String viewName = ((ControllerFourth) handler).process(paramMap,model);

        MyModel myModel=new MyModel(viewName);
        myModel.setModel(model);

        return myModel;

    }

    private Map<String,String> getParamModel(HttpServletRequest request) {
        Map<String,String> model=new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(requestName->model.put(requestName, request.getParameter(requestName)));
        return model;
    }
}
