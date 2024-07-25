package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.dto.LatestTrajectoriesDTO;
import com.fleetmanagement.api_rest.dto.TrajectoryDTO;
import com.fleetmanagement.api_rest.exception.NotFoundException;
import com.fleetmanagement.api_rest.mapper.LatestTrajectoriesDTOMapper;
import com.fleetmanagement.api_rest.mapper.TrajectoryDTOMapper;
import com.fleetmanagement.api_rest.repository.TrajectoryRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class  TrajectoryService {
    @Autowired
    private final TrajectoryRepository trajectoryRepository;
    private final TrajectoryDTOMapper trajectoryDTOMapper;
    private final LatestTrajectoriesDTOMapper latestTrajectoriesDTOMapper;

    @Autowired
    EmailService emailService;


    public List<TrajectoryDTO> getTrajectories(Integer taxi, String date){
        if(trajectoryRepository.findByTaxi(taxi, date).isEmpty()){
            throw new NotFoundException("Register not found");
        }
        return trajectoryRepository.findByTaxi(taxi, date)
                .stream()
                .map(trajectoryDTOMapper)
                .collect(Collectors.toList());
    }

    public List<LatestTrajectoriesDTO> getLatestTrajectories(){
        return trajectoryRepository.findLatestTrajectories()
                .stream()
                .map(latestTrajectoriesDTOMapper)
                .collect(Collectors.toList());
    }

    public String exportExcelMail(Integer taxiId, String date, String email) throws IOException, MessagingException {

        List<TrajectoryDTO> trajectories = getTrajectories(taxiId, date);

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

        emailService.sendMessageWithAttachment(email, "Trajectories" , "Trajectories taxi:" + taxiId);

        return "Email Sent";
    }



}
