package com.financascasal.api.domain.repository;

import com.financascasal.api.domain.model.ContaCasal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaCasalRepository extends JpaRepository<ContaCasal, UUID> {
}
