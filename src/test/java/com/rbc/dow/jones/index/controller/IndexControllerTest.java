package com.rbc.dow.jones.index.controller;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import com.rbc.dow.jones.index.exception.RecordAlreadyExists;
import com.rbc.dow.jones.index.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private IndexService indexService;

    @InjectMocks
    private IndexController controller;

    @Test
    public void getByStock_stockFound_returnStockDetails(){
        String stockTicker = "AA";
        List<WeeklyIndex> listOfStocks = new ArrayList<>();
        listOfStocks.add(new WeeklyIndex(1,"AA","1/14/2011",new BigDecimal(16.71),new BigDecimal(16.71),new BigDecimal(15.64),new BigDecimal(15.97),242963398L,-4.42849,"1.380223028","239655616",new BigDecimal(16.19),new BigDecimal(15.79),-2.47066,19,0.187852));

        when(indexService.getByStockTicker("AA")).thenReturn(listOfStocks);
        List<WeeklyIndex> returnedStockDetails = controller.getByStock("AA");
        assertEquals(1,returnedStockDetails.size());
        assertEquals(1,returnedStockDetails.get(0).getQuarter());
        assertEquals("AA",returnedStockDetails.get(0).getStock());
        assertEquals("1/14/2011", returnedStockDetails.get(0).getDate());
    }

    @Test
    public void getByStock_stockNotFound(){
        String stockTicker = "AAA";
        List<WeeklyIndex> listOfStocks = new ArrayList<>();
        when(indexService.getByStockTicker("AAA")).thenReturn(listOfStocks);
        List<WeeklyIndex> returnedStockDetails = controller.getByStock("AAA");
        assertEquals(0,returnedStockDetails.size());
    }

    @Test
    public void insertRecord_insertedSuccessfully() throws RecordAlreadyExists {
        WeeklyIndex insert =new WeeklyIndex(1,"AA","1/14/2011",new BigDecimal(16.71),new BigDecimal(16.71),new BigDecimal(15.64),new BigDecimal(15.97),242963398L,-4.42849,"1.380223028","239655616",new BigDecimal(16.19),new BigDecimal(15.79),-2.47066,19,0.187852);

        ResponseEntity<String> response =controller.insertRecord(insert);
        verify(indexService).insertData(insert);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Data inserted successfully for stock: AA", response.getBody());

    }

    @Test
    public void insertRecord_insertionFailed() {
        WeeklyIndex insert = new WeeklyIndex(1, "AA", "1/14/2011", new BigDecimal(16.71), new BigDecimal(16.71), new BigDecimal(15.64), new BigDecimal(15.97), 242963398L, -4.42849, "1.380223028", "239655616", new BigDecimal(16.19), new BigDecimal(15.79), -2.47066, 19, 0.187852);
        ResponseEntity<String> response = null;

        try {
            doThrow(new RecordAlreadyExists()).when(indexService).insertData(insert);
            response = controller.insertRecord(insert);
        } catch (RecordAlreadyExists ex) {
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("The record with stock ticker AA and date exists 12/12/2012", response.getBody());
        }
    }
}
