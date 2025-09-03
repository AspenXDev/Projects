package com.library.lms.service;

import com.library.lms.model.Fine;

import java.util.List;
import java.util.Optional;

public interface FineService {

    List<Fine> getAllFines();

    Optional<Fine> getFineById(Integer fineId);

    List<Fine> getUnpaidFinesByMemberId(Integer memberId);

    Fine createFine(Fine fine);

    Fine updateFine(Integer fineId, Fine fine);

    void deleteFine(Integer fineId);
}
