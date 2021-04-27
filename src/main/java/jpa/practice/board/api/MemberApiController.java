package jpa.practice.board.api;

import jpa.practice.board.entity.Member;
import jpa.practice.board.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/dp")
    public int dpCheck(@RequestParam("username") String username){
        int memberCount = memberService.dpCheck(username);
        return memberCount;
    }


}
