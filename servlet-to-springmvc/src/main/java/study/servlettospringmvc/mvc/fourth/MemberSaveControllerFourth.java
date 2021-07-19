package study.servlettospringmvc.mvc.fourth;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.service.MemberService;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSaveControllerFourth implements ControllerFourth{

    private final MemberService memberService;

    @Override
    public String process(Map<String, String> paramModel, Map<String, Object> model) {
        String username = paramModel.get("username");
        int age = Integer.parseInt(paramModel.get("age"));

        Member member=new Member();
        member.saveMember(username,age);
        memberService.join(member);

        model.put("member",member);

        return "save-success";
    }
}
