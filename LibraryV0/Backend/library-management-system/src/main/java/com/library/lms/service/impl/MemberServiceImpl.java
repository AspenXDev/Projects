package com.library.lms.service.impl;

import com.library.lms.mapper.MemberMapper;
import com.library.lms.repository.MemberRepository;
import com.library.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.lms.dto.*;
import com.library.lms.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.library.lms.dto.MemberPureDTO;
import com.library.lms.dto.MemberDetailsDTO;
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        return memberMapper.toDTO(memberRepository.save(member));
    }

    @Override
    public MemberDTO updateMember(Integer id, MemberDTO memberDTO) {
        Optional<Member> optional = memberRepository.findById(id);
        if (optional.isPresent()) {
            Member member = optional.get();
            member.setFullName(memberDTO.getFullName());
            member.setAddress(memberDTO.getAddress());
            member.setPhoneNumber(memberDTO.getPhoneNumber());
            member.setEmail(memberDTO.getEmail());
            return memberMapper.toDTO(memberRepository.save(member));
        }
        return null;
    }

    @Override
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO getMemberById(Integer id) {
        return memberRepository.findById(id)
                .map(memberMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> getMembersByFullName(String fullName) {
        return memberRepository.findByFullNameContainingIgnoreCase(fullName)
                .stream()
                .map(memberMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberByUserId(Integer userId) {
        return memberRepository.findByUserId(userId)
                .map(memberMapper::toDTO)
                .orElse(null);
    }
}
