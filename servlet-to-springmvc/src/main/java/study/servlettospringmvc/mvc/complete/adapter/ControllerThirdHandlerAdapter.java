package study.servlettospringmvc.mvc.complete.adapter;

import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.mvc.complete.MyHandlerAdapter;
import study.servlettospringmvc.mvc.third.ControllerThird;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerThirdHandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerThird);
    }

    @Override
    public MyModel handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerThird controller=(ControllerThird) handler;
        MyModel model = controller.process(getParamModel(request));

        return model;
    }

    private Map<String,String> getParamModel(HttpServletRequest request) {
        Map<String,String> model=new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(requestName->model.put(requestName, request.getParameter(requestName)));
        return model;
    }
}
