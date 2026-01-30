package com.riskshield.customer.repository;

import com.riskshield.customer.domain.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile,String> {

    Optional<CustomerProfile> findByEmail(String email);

    CustomerProfile findByPhoneNumber(String phoneNUmber);

    boolean existsByCustomerId(String customerId);

    Optional<CustomerProfile> findByCustomerIdAndName(String customerId, String name);



}
