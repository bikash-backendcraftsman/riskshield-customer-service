package com.riskshield.customer.service.impl;

import com.riskshield.customer.exception.DuplicateContactExistException;
import com.riskshield.customer.exception.DuplicateEmailException;
import com.riskshield.customer.repository.CustomerReadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerUniquenessService {

    private final CustomerReadRepository customerReadRepository;

    public CustomerUniquenessService(CustomerReadRepository customerReadRepository) {
        this.customerReadRepository = customerReadRepository;
    }


    public void ensureUnique(String email, String mobileNumber){

        ensureEmailIsUnique(email);
        ensureMobileIsUnique(mobileNumber);
    }

    private void ensureEmailIsUnique(String email) {
        if(customerReadRepository.existsByEmail(email)){
            log.warn("Duplicate email registration attempt : {}",email);

            throw new DuplicateEmailException("Email is already registered");
        }
    }

    private void ensureMobileIsUnique(String mobileNumber){
        if(customerReadRepository.existsByPhoneNumber(mobileNumber)){
            log.warn("Contact is already registered : {}",mobileNumber);

            throw new DuplicateContactExistException("Contact nuumber is already registred");
        }
    }
}
