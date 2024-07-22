package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Member;
import com.agents.java_book_library.exceptions.MemberNotFound;
import com.agents.java_book_library.exceptions.UsernameAlreadyExists;
import com.agents.java_book_library.mappers.MemberMapper;
import com.agents.java_book_library.model.MemberDTO;
import com.agents.java_book_library.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private MemberRepository memberRepository;
    private final MemberMapper mapper;

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(mapper::toMemberDTO).collect(Collectors.toList());
    }

    public MemberDTO getOneMemberById(Long memberId) {
        return mapper.toMemberDTO(memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFound(null, memberId)));
    }

    public MemberDTO getOneMemberByUsername(String username) {
        return mapper.toMemberDTO(memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFound(username, null)));
    }

    public MemberDTO createMember(MemberDTO memberDTO) {
        try {
            Member member = mapper.toMember(memberDTO);
            return mapper.toMemberDTO(memberRepository.save(member));
        } catch (DataIntegrityViolationException e) {
            throw new UsernameAlreadyExists(memberDTO.getUsername());
        }
    }

    public MemberDTO updateMember(MemberDTO memberDTO) {
        MemberDTO found;
        if (memberDTO.getMemberId() != null) {
            found = getOneMemberById(memberDTO.getMemberId());
        } else {
            found = getOneMemberByUsername(memberDTO.getUsername());
        }

        found.setUsername(memberDTO.getUsername());
        found.setEmail(memberDTO.getEmail());
        found.setAddress(memberDTO.getAddress());
        found.setPhoneNumber(memberDTO.getPhoneNumber());
        found.setLoanBook(memberDTO.getLoanBook());

        Member member = mapper.toMember(found);

        try {
            return mapper.toMemberDTO(memberRepository.save(member));
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
