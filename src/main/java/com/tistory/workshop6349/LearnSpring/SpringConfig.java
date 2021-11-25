package com.tistory.workshop6349.LearnSpring;

import com.tistory.workshop6349.LearnSpring.repository.MemberRepository;
import com.tistory.workshop6349.LearnSpring.repository.MemoryMemberRepository;
import com.tistory.workshop6349.LearnSpring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
