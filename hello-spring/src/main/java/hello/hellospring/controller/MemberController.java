package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private  MemberService memberService;

    @Autowired  //생성자에 넣어주는 어노테이션
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }




    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }   //get방식의 컨트롤러

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setname(form.getName());
        memberService.join(member);
//        List<Member> a = memberService.findMembers();
//        for(Member c : a) {
//            System.out.println(c.getname());
//        }
        return "redirect:/";
    }   //post방식의 컨트롤러

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
