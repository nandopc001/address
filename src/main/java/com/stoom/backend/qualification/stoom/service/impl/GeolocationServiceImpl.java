package com.stoom.backend.qualification.stoom.service.impl;

import com.stoom.backend.qualification.stoom.model.Address;
import com.stoom.backend.qualification.stoom.service.GeolocationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Component
public class GeolocationServiceImpl implements GeolocationService {

    @Value("${service.google.geolocation.apikey}")
    private String googleGeolocationApiKey;

    @Override
    public void getGeolocation(final Address address) {
        // TODO Auto-generated method stub

        try {

            final LatLng latLng = this.getGoogleGeoLocation(String.format("%s %s %s %s", address.getState(),
                    address.getNeighbourhood(), address.getCity(), address.getStreetName()));

            address.setLatitude(latLng.lat);
            address.setLongitude(latLng.lng);

        } catch (InterruptedException | ApiException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private LatLng getGoogleGeoLocation(final String format) throws ApiException, InterruptedException, IOException {

        final GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey(this.googleGeolocationApiKey).build();

        final GeocodingResult[] result = GeocodingApi.geocode(geoApiContext, format).await();
        return (result[0].geometry.location);
    }
}