package com.stoom.backend.qualification.stoom.service;

import com.stoom.backend.qualification.stoom.model.Address;

public interface GeolocationService {
    void getGeolocation(Address address);
}
