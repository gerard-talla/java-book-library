package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Member;
import com.agents.java_book_library.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        // TODO: Pagination
        return memberRepository.findAll();
    }

    public Member getOneMemberById(Integer memberId) {
        // TODO: Add custom exception
        return memberRepository.findById(memberId).orElseThrow(() -> null);
    }

    public Member getOneMemberByUsername(String username) {
        // TODO: Add custom exception
        return memberRepository.findByUsername(username).orElseThrow(() -> null);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member found;
        if (member.getMemberId() != null) {
            found = getOneMemberById(member.getMemberId());
        } else {
            found = getOneMemberByUsername(member.getUsername());
        }

        found = Member.builder()
                .username(member.getUsername())
                .email(member.getEmail())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();

        return memberRepository.save(found);
    }

    public void deleteMemberById(Integer memberId) {
        // TODO: Add custom exception
        memberRepository.findById(memberId).orElseThrow(() -> null);
        memberRepository.deleteById(memberId);
    }

    public void deleteMemberByUsername(String username) {
        // TODO: Add custom exception
        Member found = memberRepository.findByUsername(username).orElseThrow(() -> null);
        memberRepository.delete(found);
    }
}
