package study.servlettospringmvc.mvc.third;

import study.servlettospringmvc.mvc.MyModel;

import java.util.Map;

public class MemberFormControllerThird implements ControllerThird{

    @Override
    public MyModel process( Map<String, String> parmaModel) {
        MyModel myModel=new MyModel("member-form");

        return myModel;
    }


}
