package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.exception.BadRequestException;
import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Double.NaN;

@Service
public class TaxiService {
    @Autowired
    private TaxiRepository taxiRepository;

    public List<TaxiModel> getTaxis(String plate, Integer page, Integer limit) throws BadRequestException {
        if(page == null){
          throw new BadRequestException("Page number cannot be null");
        }
        if(limit == null){
            throw new BadRequestException("Limit cannot be null ");
        }
        Pageable pageable = PageRequest.of(page,limit);
        return taxiRepository.findByPlateContaining(plate, pageable) ;
    }
}

