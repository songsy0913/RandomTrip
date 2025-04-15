package kr.ac.hs.RandomTrip.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

//    @GetMapping("/register")
//    String register() {
//        return "register.html";
//    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody MemberDto dto) {
        try {
            memberService.addMember(dto.username(), dto.password(), dto.displayName());
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/login")
    String login() {
        return "login.html";
    }

    @PostMapping("/member")
    String addMember(String username,
                     String password,
                     String displayName) throws Exception {
        memberService.addMember(username, password, displayName);
        return "redirect:/login";
    }

    @GetMapping("/my-page")
    String myPage() {
        return "mypage.html";
    }

    @GetMapping("/")
    String main() {
        return "main.html";
    }


}
