package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.dto.LatestTrajectoriesDTO;
import com.fleetmanagement.api_rest.dto.TrajectoryDTO;

import com.fleetmanagement.api_rest.service.TrajectoryService;
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
                                    @RequestParam String date) throws IOException {

        List<TrajectoryDTO> trajectories = trajectoryService.getTrajectories(taxiId, date);

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Trajectories");

        for(int r=0; r<trajectories.size(); r++){
            Row row = sheet.createRow(r);
                Cell cellId = row.createCell(0);
                cellId.setCellValue(String.valueOf(trajectories.get(r).getId()));
                Cell cellPlate = row.createCell(1);
                cellPlate.setCellValue(String.valueOf(trajectories.get(r).getPlate( )));
                Cell cellDate = row.createCell(2);
                cellDate.setCellValue(String.valueOf(trajectories.get(r).getDate().toLocalDateTime()));
                Cell cellLat = row.createCell(3);
                cellLat.setCellValue(String.valueOf(trajectories.get(r).getLatitude()));
                Cell cellLong = row.createCell(4);
                cellLong.setCellValue(String.valueOf(trajectories.get(r).getLongitude()));

        }

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        return "Excel creado";
    }
}
