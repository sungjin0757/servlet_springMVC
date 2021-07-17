package study.servlettospringmvc.mvc;

import org.springframework.stereotype.Component;

@Component
public class MyViewResolverImpl implements MyViewResolver{

    @Override
    public MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/"+viewName+".jsp");
    }


}
