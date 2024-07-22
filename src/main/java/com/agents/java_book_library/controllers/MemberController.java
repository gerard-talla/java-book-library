package com.agents.java_book_library.controllers;

import com.agents.java_book_library.api.MemberApi;
import com.agents.java_book_library.model.MemberDTO;
import com.agents.java_book_library.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberApi {

    private final MemberService memberService;

    @Override
    public ResponseEntity<MemberDTO> addMember(MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberService.createMember(memberDTO));
    }

    @Override
    public ResponseEntity<Void> deleteMember(Long memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<Void> deleteMemberByUsername(String username) {
        memberService.deleteMemberByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<List<MemberDTO>> findAllMember() {
        return MemberApi.super.findAllMember();
    }

    @Override
    public ResponseEntity<MemberDTO> getMemberById(Long memberId) {
        return MemberApi.super.getMemberById(memberId);
    }

    @Override
    public ResponseEntity<MemberDTO> getMemberByUsername(String username) {
        return ResponseEntity.ok(memberService.getOneMemberByUsername(username));
    }

    @Override
    public ResponseEntity<MemberDTO> updateMember(MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(memberService.updateMember(memberDTO));
    }
}
