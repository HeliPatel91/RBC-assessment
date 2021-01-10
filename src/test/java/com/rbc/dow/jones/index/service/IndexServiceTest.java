package com.rbc.dow.jones.index.service;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import com.rbc.dow.jones.index.entity.WeeklyIndexId;
import com.rbc.dow.jones.index.exception.RecordAlreadyExists;
import com.rbc.dow.jones.index.repository.WeeklyIndexRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexServiceTest {

    @Mock
    private WeeklyIndexRepository indexRepository;

    @InjectMocks
    private IndexService indexService;

    @Test
    public void checkIfAlreadyExists_existsTickerDate_true(){
        String stock = "AA";
        String date = "15/7/2012";
        WeeklyIndexId primaryKey = new WeeklyIndexId(stock,date);
        Optional<WeeklyIndex> returnValue = Optional.of(new WeeklyIndex());

        when(indexRepository.findById(primaryKey)).thenReturn(returnValue);
        assertTrue(indexService.checkIfAlreadyExists(stock,date));
    }

    @Test
    public void checkIfAlreadyExists_existsDateOnly_false(){
        String stock = "AB";
        String date = "15/7/2012";
        WeeklyIndexId primaryKey = new WeeklyIndexId(stock,date);
        Optional<WeeklyIndex> returnValue = Optional.ofNullable(null);

        when(indexRepository.findById(primaryKey)).thenReturn(returnValue);
        assertFalse(indexService.checkIfAlreadyExists(stock,date));
    }

    @Test
    public void checkIfAlreadyExists_nullData_false(){
        String stock = null;
        String date = null;
        WeeklyIndexId primaryKey = new WeeklyIndexId(stock,date);
        Optional<WeeklyIndex> returnValue = Optional.ofNullable(null);

        when(indexRepository.findById(primaryKey)).thenReturn(returnValue);
        assertFalse(indexService.checkIfAlreadyExists(stock,date));
    }

    @Test
    public void checkIfAlreadyExists_emptyData_false(){
        String stock = "";
        String date = "";
        WeeklyIndexId primaryKey = new WeeklyIndexId(stock,date);
        Optional<WeeklyIndex> returnValue = Optional.ofNullable(null);

        when(indexRepository.findById(primaryKey)).thenReturn(returnValue);
        assertFalse(indexService.checkIfAlreadyExists(stock,date));
    }

    @Test
    public void getByStockTicker_found() {
        String stockTicker = "AA";
        List<WeeklyIndex> listOfStocks = new ArrayList<>();
        listOfStocks.add(new WeeklyIndex(1,"AA","1/14/2011",new BigDecimal(16.71),new BigDecimal(16.71),new BigDecimal(15.64),new BigDecimal(15.97),242963398L,-4.42849,"1.380223028","239655616",new BigDecimal(16.19),new BigDecimal(15.79),-2.47066,19,0.187852));

        when(indexRepository.findByStock(stockTicker)).thenReturn(listOfStocks);
        List<WeeklyIndex> returnedStocks = indexService.getByStockTicker(stockTicker);
        assertEquals(1,returnedStocks.size());
        assertEquals(1,returnedStocks.get(0).getQuarter());
        assertEquals("AA",returnedStocks.get(0).getStock());
        assertEquals("1/14/2011", returnedStocks.get(0).getDate());
    }

    @Test
    public void getByStockTicker_notFound() {
        String stockTicker = "AA";
        when(indexRepository.findByStock(stockTicker)).thenReturn(null);
        List<WeeklyIndex> returnedStocks = indexService.getByStockTicker(stockTicker);
        assertNull(returnedStocks);
    }

    @Test
    public void insertData_alreadyExists_throwException() {
        WeeklyIndex index = new WeeklyIndex();
        index.setStock("AA");
        index.setDate("12/12/2012");
        WeeklyIndexId primaryKey = new WeeklyIndexId("AA","12/12/2012");
        Optional<WeeklyIndex> returnValue = Optional.of(new WeeklyIndex());

        when(indexRepository.findById(primaryKey)).thenReturn(returnValue);
        try {
            indexService.insertData(index);
        }catch(RecordAlreadyExists ex){
            assertEquals("The record with stock ticker AA and date exists 12/12/2012", ex.getMessage());
        }
        verify(indexRepository, times(0)).save(any());
    }

    @Test
    public void insertData_alreadyExists_successful() throws RecordAlreadyExists {
        WeeklyIndex index = new WeeklyIndex();
        index.setStock("AA");
        index.setDate("12/12/2012");
        WeeklyIndexId primaryKey = new WeeklyIndexId("AA","12/12/2012");
        Optional<WeeklyIndex> returnValue = Optional.ofNullable(null);

        when(indexRepository.findById(primaryKey)).thenReturn(returnValue);

        indexService.insertData(index);
        verify(indexRepository, times(1)).save(index);
    }
}
