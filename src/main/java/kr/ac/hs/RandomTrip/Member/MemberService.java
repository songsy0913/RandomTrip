package kr.ac.hs.RandomTrip.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void addMember(String username,
                          String password,
                          String displayName) throws Exception {
        if(username.length() < 4 || password.length() < 4){
            throw new Exception("너무 짧음");
        }
        if(memberRepository.findByUsername(username).isPresent()){
            throw new Exception("존재하는 아이디");
        }
        Member member = new Member();
        member.setUsername(username);
        var hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
