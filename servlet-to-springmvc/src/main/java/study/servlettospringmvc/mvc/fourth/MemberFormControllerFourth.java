package study.servlettospringmvc.mvc.fourth;

import java.util.Map;

public class MemberFormControllerFourth implements ControllerFourth{

    @Override
    public String process(Map<String, String> paramModel,Map<String,Object> model) {
        return "member-form";
    }
}
