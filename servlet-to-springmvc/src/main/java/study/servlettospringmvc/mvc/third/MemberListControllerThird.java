package study.servlettospringmvc.mvc.third;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.service.MemberService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberListControllerThird implements ControllerThird{

    private final MemberService memberService;

    @Override
    public MyModel process( Map<String, String> paramModel) {
        List<Member> members = memberService.findAll();
        MyModel myModel=new MyModel("members");

        myModel.getModel().put("members",members);

        return myModel;
    }
}
