package com.rbc.dow.jones.index.util;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CSVHelperTest {

    @InjectMocks
    private CSVHelper csvHelper;

    /*@Mock
    private CSVRecord csvRecord;*/

    @Test
    public void removeDollar_dollarExists(){
        String moneyValue= "$15.74";
        assertEquals("15.74",csvHelper.removeDollar(moneyValue));
    }

    @Test
    public void removeDollar_dollarDoesNotExists(){
        String moneyValue= "15.74";
        assertEquals("15.74",csvHelper.removeDollar(moneyValue));
    }

    @Test
    public void checkCSVFormat_compatible() {
        MockMultipartFile csvFile = new MockMultipartFile("data", "filename.csv", "text/csv", "some csv".getBytes());
        assertTrue(csvHelper.checkCSVFormat(csvFile));
    }

    @Test
    public void checkCSVFormat_NotCompatible() {
        MockMultipartFile jsonFile = new MockMultipartFile("data", "filename.json", "application/json", "some csv".getBytes());
        assertFalse(csvHelper.checkCSVFormat(jsonFile));
    }

    //TODO: As below did not work out as can not mock CSVRecord(final class) try some other way
    /*@Test
    public void convertCsvRecordToWeeklyIndex_oneRow_allValidData(){
        when(csvRecord.get("quarter")).thenReturn("1");
        when(csvRecord.get("stock")).thenReturn("$15.95");
        when(csvRecord.get("date")).thenReturn("12/12/2012");
        when(csvRecord.get("open")).thenReturn("$17.98");
        when(csvRecord.get("low")).thenReturn("$17.98");
        when(csvRecord.get("close")).thenReturn("$18.00");
        when(csvRecord.get("volume")).thenReturn("24658393052");
        when(csvRecord.get("percent_change_price")).thenReturn("24658393052");
        when(csvRecord.get("percent_change_volume_over_last_wk")).thenReturn("0.24658393052");
        when(csvRecord.get("previous_weeks_volume")).thenReturn("25913713");
        when(csvRecord.get("next_weeks_open")).thenReturn("$23.12");
        when(csvRecord.get("next_weeks_close")).thenReturn("$25.12");
        when(csvRecord.get("percent_change_next_weeks_price")).thenReturn("24.12438264");
        when(csvRecord.get("days_to_next_dividend")).thenReturn("18");
        when(csvRecord.get("next_weeks_close")).thenReturn("$25.12");

        WeeklyIndex mappedObject = csvHelper.convertCsvRecordToWeeklyIndex(csvRecord);
        assertEquals("12/12/2012",mappedObject.getDate());
        assertEquals("1",mappedObject.getQuarter());
        assertEquals("15.95",mappedObject.getStock());
        assertEquals("17.98",mappedObject.getOpen());
        assertEquals("17.98",mappedObject.getLow());
        assertEquals("18.00",mappedObject.getClose());
        assertEquals("24658393052",mappedObject.getVolume());
        assertEquals("24658393052",mappedObject.getPercentChangePrice());
        assertEquals("0.24658393052",mappedObject.getPercentChangeVolumeOverLastWk());
        assertEquals("25913713",mappedObject.getPreviousWeeksVolume());
        assertEquals("23.12",mappedObject.getNextWeeksOpen());
        assertEquals("25.12",mappedObject.getNextWeeksClose());
        assertEquals("24.12438264",mappedObject.getPercentChangeNextWeeksPrice());
    }*/
}
