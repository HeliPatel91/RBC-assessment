package com.rbc.dow.jones.index.controller;

import com.rbc.dow.jones.index.service.IndexService;
import com.rbc.dow.jones.index.util.CSVHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/upload")
//Controller to upload bulk data in CSV .
//TODO: Can add the download API to download all the data in CSV file
public class UploadController {

    private IndexService indexService;

    @Autowired
    public UploadController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping(name ="/stocks" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload data via CSV file", description = "Upload bulk data by CSV file in the given format (all currency in $ only):\n" +
            "quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,\n"+
            "previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data uploaded successfully"),
            @ApiResponse(responseCode = "406", description = "Type of the file should be CSV"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file){
        String message = "";

        if (CSVHelper.checkCSVFormat(file)) {
            try {
                indexService.uploadData(file);

                message = "File uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
            }
        }
        message = "Please upload CSV type file!";
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }
}
