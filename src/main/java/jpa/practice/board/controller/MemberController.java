package jpa.practice.board.controller;

import jpa.practice.board.dto.MemberDto;
import jpa.practice.board.entity.Member;
import jpa.practice.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.SessionImpl;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String MemberJoinForm(Model model){
        model.addAttribute("MemberDto", new MemberDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String joinMember(MemberDto form){
        Member member = Member.builder()
                .memberName(form.getMemberName())
                .password(form.getPassword())
                .build();
        Long memberId = memberService.joinMember(member);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginMember(MemberDto form, HttpServletRequest request){

        Member member = Member.builder()
                .memberName(form.getMemberName())
                .password(form.getPassword())
                .build();
        Member findMember = memberService.LoginMember(member);
        //session 객체생성
        HttpSession session = request.getSession();
        session.setAttribute("Member",findMember);
        return "redirect:/";
    }

}
