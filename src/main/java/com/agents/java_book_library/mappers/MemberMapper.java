package com.agents.java_book_library.mappers;

import com.agents.java_book_library.domains.Address;
import com.agents.java_book_library.domains.LoanBook;
import com.agents.java_book_library.domains.Member;
import com.agents.java_book_library.model.AddressDTO;
import com.agents.java_book_library.model.LoanBookDTO;
import com.agents.java_book_library.model.MemberDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {

    public MemberDTO toMemberDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberId( member.getMemberId() );
        memberDTO.setUsername( member.getUsername() );
        memberDTO.setEmail( member.getEmail() );
        memberDTO.setAddress( toAddressDTO( member.getAddress() ) );
        memberDTO.setPhoneNumber( member.getPhoneNumber() );
        memberDTO.setLoanBook(toListLoanBookDTO(member.getLoanBooks()));

        return memberDTO;
    }
    public Member toMember(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.memberId(memberDTO.getMemberId());
        member.username(memberDTO.getUsername());
        member.email(memberDTO.getEmail());
        member.phoneNumber(memberDTO.getPhoneNumber());
        member.address(toAddress(memberDTO.getAddress()));
        member.loanBooks(toListLoanBook(memberDTO.getLoanBook()));

        return member.build();
    }

    public abstract List<LoanBookDTO> toListLoanBookDTO(List<LoanBook> loanBookList);
    public abstract List<LoanBook> toListLoanBook(List<LoanBookDTO> loanBookDTOList);
    public abstract AddressDTO toAddressDTO(Address address);
    public abstract Address toAddress(AddressDTO addressDTO);
}
