package com.stoom.backend.qualification.stoom.service;

import java.util.List;

import com.stoom.backend.qualification.stoom.model.Address;


public interface AddressService {
    
    Address createAddress(Address address);

    Address findAddressById(Long id);

    List<Address> findAllAddress();

    Address updateAddress(Address address);

    Boolean deleteAddress(Address address);

}