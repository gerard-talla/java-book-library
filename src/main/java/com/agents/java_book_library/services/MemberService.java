package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Member;
import com.agents.java_book_library.exceptions.MemberNotFound;
import com.agents.java_book_library.exceptions.UsernameAlreadyExists;
import com.agents.java_book_library.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Member getOneMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFound(null, memberId));
    }

    public Member getOneMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFound(username, null));
    }

    public Member createMember(Member member) {
        try {
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameAlreadyExists(member.getUsername());
        }
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

        try {
            return memberRepository.save(found);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameAlreadyExists(found.getUsername());
        }
    }

    public void deleteMemberById(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFound(null, memberId));
        memberRepository.deleteById(memberId);
    }

    public void deleteMemberByUsername(String username) {
        Member found = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFound(username, null));
        memberRepository.delete(found);
    }
}
