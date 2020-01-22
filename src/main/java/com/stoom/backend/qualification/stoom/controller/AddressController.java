package com.stoom.backend.qualification.stoom.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.stoom.backend.qualification.stoom.model.Address;
import com.stoom.backend.qualification.stoom.service.AddressService;
import com.stoom.backend.qualification.stoom.service.GeolocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private GeolocationService geolocationService;

    @PostMapping("/address")
    public ResponseEntity<Address> createAddress(@Valid final @RequestBody Address address) {
        ResponseEntity<Address> responseEntity = null;
        if (address != null) {
            if ((address.getLatitude() == null || address.getLatitude().isNaN())
                    || (address.getLongitude() == null || address.getLongitude().isNaN())) {
                geolocationService.getGeolocation(address);
            }
            final Address savedAddress = addressService.createAddress(address);
            final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedAddress.getId()).toUri();
            responseEntity = ResponseEntity.created(location).body(address);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> findAddressById(@PathVariable("id") final Long id) {
        ResponseEntity<Address> responseEntity = null;
        final Address address = addressService.findAddressById(id);
        if (address != null) {
            responseEntity = ResponseEntity.ok(address);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @GetMapping("/address")
    public List<Address> findAllAddress() {

        final List<Address> address = addressService.findAllAddress();
        return address;
    }

    @PutMapping("/address")
    public ResponseEntity<Address> updateAddress(@RequestBody final Address address) {
        ResponseEntity<Address> responseEntity = null;
        final Address savedAddress = addressService.updateAddress(address);
        if (savedAddress != null) {
            responseEntity = ResponseEntity.ok(savedAddress);
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/address")
    public ResponseEntity<Address> deleteAddress(@RequestBody final Address address) {
        ResponseEntity<Address> responseEntity = null;
        final Boolean deletedAddress = addressService.deleteAddress(address);
        if(deletedAddress){
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }
}