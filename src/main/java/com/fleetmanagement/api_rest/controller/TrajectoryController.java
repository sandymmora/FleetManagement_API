package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.dto.LatestTrajectoriesDTO;
import com.fleetmanagement.api_rest.dto.TrajectoryDTO;

import com.fleetmanagement.api_rest.service.EmailService;
import com.fleetmanagement.api_rest.service.TrajectoryService;
import jakarta.mail.MessagingException;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/trajectories")
public class TrajectoryController {

    @Autowired
    TrajectoryService trajectoryService;


    @GetMapping()
    public List<TrajectoryDTO> getTrajectories(@RequestParam Integer  taxiId,
                                               @RequestParam String date){
        return trajectoryService.getTrajectories(taxiId,date);
    }
    @GetMapping("/latest")
    public List<LatestTrajectoriesDTO> getLatestTrajectories(){
        return trajectoryService.getLatestTrajectories();
    }
    @GetMapping("/export")
    public String trajectoriesExcel(@RequestParam Integer taxiId,
                                    @RequestParam String date,
                                    @RequestParam String email) throws IOException, MessagingException {


        return trajectoryService.exportExcelMail(taxiId, date, email);
    }

}
