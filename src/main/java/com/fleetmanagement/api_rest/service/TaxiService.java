package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.exception.BadRequestException;
import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiService {
    @Autowired
    private TaxiRepository taxiRepository;

    public List<TaxiModel> getTaxis(String plate, Integer page, Integer limit) throws BadRequestException {
        Pageable pageable = PageRequest.of(page,limit);
        if(taxiRepository.findByPlateContaining(plate, pageable).isEmpty()){
          throw new BadRequestException("Not Found");
        }
        return taxiRepository.findByPlateContaining(plate, pageable) ;
    }
}

