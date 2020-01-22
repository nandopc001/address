package com.stoom.backend.qualification.stoom.repository;

import com.stoom.backend.qualification.stoom.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}

