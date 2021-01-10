package com.rbc.dow.jones.index.controller;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import com.rbc.dow.jones.index.exception.RecordAlreadyExists;
import com.rbc.dow.jones.index.service.IndexService;
import com.rbc.dow.jones.index.util.CSVHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//Controller to retrieve stocks data or to insert the record
public class IndexController {

    private IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/stock/{stockTicker}")
    @Operation(summary = "Find Stocks by stock ticker", description = "Provide the stock ticker name to retrieve all the stocks for all quarters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public List<WeeklyIndex> getByStock(@PathVariable final String stockTicker){
        return indexService.getByStockTicker(stockTicker);
    }

    @PostMapping("/stock")
    @Operation(summary = "Insert record", description = "Insert one single record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Data inserted successfully"),
            @ApiResponse(responseCode = "400", description = "Something wrong in the request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> insertRecord(@RequestBody WeeklyIndex singleRecord){
        String message = "";
            try {
                indexService.insertData(singleRecord);
                message = "Data inserted successfully for stock: " + singleRecord.getStock();
                return ResponseEntity.status(HttpStatus.CREATED).body(message);
            } catch (RecordAlreadyExists e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
    }

}
