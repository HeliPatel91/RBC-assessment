package com.rbc.dow.jones.index.service;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import com.rbc.dow.jones.index.entity.WeeklyIndexId;
import com.rbc.dow.jones.index.exception.RecordAlreadyExists;
import com.rbc.dow.jones.index.repository.WeeklyIndexRepository;
import com.rbc.dow.jones.index.util.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class IndexService {

    private static Logger logger = LoggerFactory.getLogger(IndexService.class);

    private WeeklyIndexRepository indexRepository;

    @Autowired
    public IndexService(WeeklyIndexRepository indexRepository){
        this.indexRepository = indexRepository;
    }

    //This service method is responsible for csv to entity object coversion and storing in DB
    public void uploadData(MultipartFile file) throws RecordAlreadyExists{
        try {
            List<WeeklyIndex> bulkData = CSVHelper.mapDataToModel(file.getInputStream());

            logger.info("Inserting data in the database");
            for(WeeklyIndex row:bulkData){
                if(row != null) {
                    try {
                        insertData(row);
                    }catch (RecordAlreadyExists ex){
                        logger.error("Record already exists " +ex.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Fail to parse file "+e.getMessage());
            throw new RuntimeException("Fail to parse file: " + e.getMessage());
        }
    }

    //this method is responsible for inserting data in DB after checking duplicates
    public void insertData(WeeklyIndex index) throws RecordAlreadyExists {
            if(checkIfAlreadyExists(index.getStock(), index.getDate())) {
                logger.error("Record already exists {} {} ",index.getStock(),index.getDate());
                throw new RecordAlreadyExists("The record with stock ticker " + index.getStock() + " and date exists " + index.getDate());
            }
            indexRepository.save(index);
    }

    //Unused at the moment but can be used to retrieve complete data
    public List<WeeklyIndex> getAll(){
        return indexRepository.findAll();
    }

    //repository get operation to retrieve data by stockTicker
    public List<WeeklyIndex> getByStockTicker(String stockTicker) {
        return indexRepository.findByStock(stockTicker);
    }

    //checking if record with same stock and date exists
    public boolean checkIfAlreadyExists(String stock, String date){
        WeeklyIndexId primaryKey = new WeeklyIndexId(stock,date);
        return indexRepository.findById(primaryKey).isPresent();
    }
}
