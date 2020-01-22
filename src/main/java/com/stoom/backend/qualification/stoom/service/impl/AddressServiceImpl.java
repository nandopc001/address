package com.stoom.backend.qualification.stoom.service.impl;

import java.util.List;

import com.stoom.backend.qualification.stoom.model.Address;
import com.stoom.backend.qualification.stoom.repository.AddressRepository;
import com.stoom.backend.qualification.stoom.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Address createAddress(final Address address) {
        // TODO Auto-generated method stub
        final Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    @Override
    public Address findAddressById(final Long id) {
        // TODO Auto-generated method stub
        final Address address = addressRepository.findById(id).get();
        return address;
    }

    @Override
    public List<Address> findAllAddress() {
        // TODO Auto-generated method stub
        return addressRepository.findAll();
    }

    @Override
    public Address updateAddress(final Address address) {
        // TODO Auto-generated method stub
        final int originalAddressHash = addressRepository.findById(address.getId()).hashCode();
        if (address.hashCode() == originalAddressHash) {
            return address;
        }
        final Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    @Override
    public Boolean deleteAddress(final Address address) {
        // TODO Auto-generated method stub
        Boolean deleted = false;
        if(addressRepository.existsById(address.getId())){
            addressRepository.delete(address);
            deleted = true;
        }
        return deleted;        
    }
}