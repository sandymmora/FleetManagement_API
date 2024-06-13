package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TaxiService {
    @Autowired
    private TaxiRepository taxiRepository;

    public ArrayList<TaxiModel> getTaxis() {
        return (ArrayList<TaxiModel>) taxiRepository.findAll();
    }
}
