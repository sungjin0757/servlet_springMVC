package study.servlettospringmvc.mvc.third;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.mvc.MyModel;
import study.servlettospringmvc.service.MemberService;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSaveControllerThird implements ControllerThird{

    private final MemberService memberService;

    @Override
    public MyModel process( Map<String, String> paramModel) {


        String username = paramModel.get("username");
        int age = Integer.parseInt(paramModel.get("age"));

        Member member=new Member();
        member.saveMember(username,age);

        memberService.join(member);

        MyModel myModel=new MyModel("save-success");
        myModel.getModel().put("member",member);

        return myModel;
    }
}
