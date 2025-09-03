package com.library.lms.service.impl;

import com.library.lms.model.Fine;
import com.library.lms.repository.FineRepository;
import com.library.lms.repository.MemberRepository;
import com.library.lms.repository.UserRepository;
import com.library.lms.service.FineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public FineServiceImpl(FineRepository fineRepository,
                           MemberRepository memberRepository,
                           UserRepository userRepository) {
        this.fineRepository = fineRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @Override
    public Optional<Fine> getFineById(Integer fineId) {
        return fineRepository.findById(fineId);
    }

    @Override
    public List<Fine> getFinesByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> memberRepository.findByUser_UserId(user.getUserId()))
                .map(member -> fineRepository.findByLoan_Member_MemberId(member.getMemberId()))
                .orElse(List.of());
    }

    @Override
    public List<Fine> getUnpaidFinesByMemberId(Integer memberId) {
        return fineRepository.findByLoan_Member_MemberIdAndPaidFalse(memberId);
    }

    @Override
    public Fine createFine(Fine fine) {
        return fineRepository.save(fine);
    }

    @Override
    public Fine updateFine(Integer fineId, Fine fine) {
        Fine existing = fineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found"));
        existing.setAmount(fine.getAmount());
        existing.setPaid(fine.getPaid());
        return fineRepository.save(existing);
    }

    @Override
    public void deleteFine(Integer fineId) {
        fineRepository.deleteById(fineId);
    }
}
