package study.servlettospringmvc.mvc.fourth;

import lombok.RequiredArgsConstructor;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.service.MemberService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberListControllerFourth implements ControllerFourth{

    private final MemberService memberService;

    @Override
    public String process(Map<String, String> paramModel,Map<String,Object> model) {
        List<Member> members = memberService.findAll();

        model.put("members",members);
        return "members";
    }
}
