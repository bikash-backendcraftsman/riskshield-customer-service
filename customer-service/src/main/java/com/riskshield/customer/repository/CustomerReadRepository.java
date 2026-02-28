package com.riskshield.customer.repository;

import com.riskshield.customer.domain.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerReadRepository extends JpaRepository<CustomerProfile, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);


}
