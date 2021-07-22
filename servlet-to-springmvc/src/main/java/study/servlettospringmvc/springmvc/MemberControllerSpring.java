package study.servlettospringmvc.springmvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.servlettospringmvc.domain.Member;
import study.servlettospringmvc.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spring-mvc/members")
@Slf4j
public class MemberControllerSpring {

    private final MemberService memberService;

    @GetMapping("/save")
    public String memberForm(){
        return "member-form";
    }

    @GetMapping("/{memberId}")
    public String memberDetail(@PathVariable Long memberId,Model model){
        Member member = memberService.findOne(memberId);
        model.addAttribute("member",member);

        return "save-success";
    }

    @PostMapping("/save")
    public String memberAdd(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
        memberService.join(member);
        log.info("Call MemberAdd memberId : {}",member.getId());
        redirectAttributes.addAttribute("memberId",member.getId());
        return "redirect:/spring-mvc/members/{memberId}";
    }

    @GetMapping
    public String memberList(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);

        return "members";
    }
}
