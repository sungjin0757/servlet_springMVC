package study.servlettospringmvc.mvc.third;

import study.servlettospringmvc.mvc.MyModel;

import java.util.Map;

public interface ControllerThird {

    MyModel process(Map<String,String> paramModel);
}
