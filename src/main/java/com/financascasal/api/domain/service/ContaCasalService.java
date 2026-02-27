package com.financascasal.api.domain.service;

import com.financascasal.api.domain.model.ContaCasal;
import com.financascasal.api.domain.repository.ContaCasalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContaCasalService {

    private final ContaCasalRepository contaCasalRepository;

    @Transactional
    public ContaCasal criar(ContaCasal contaCasal) {
        return contaCasalRepository.save(contaCasal);
    }
}
