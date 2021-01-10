package com.rbc.dow.jones.index.util;

import com.rbc.dow.jones.index.entity.WeeklyIndex;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class CSVHelper {

    private static Logger logger = LoggerFactory.getLogger(CSVHelper.class);

    private static DecimalFormat decimalFormat = new DecimalFormat("0.000000");

    public static String TYPE = "text/csv";
    static String[] HEADERS = { "quarter" , "stock", "date", "open", "high", "low", "close", "volume", "percent_change_price",
                            "percent_change_volume_over_last_wk", "previous_weeks_volume", "next_weeks_open", "next_weeks_close",
                            "percent_change_next_weeks_price", "days_to_next_dividend", "percent_return_next_dividend"};

    public static boolean checkCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<WeeklyIndex> mapDataToModel(InputStream inputStream) {
        logger.info("Converting input data to model");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            return csvParser.getRecords().stream().map( csvRecord -> {
                try {
                    return convertCsvRecordToWeeklyIndex(csvRecord);
                }
                catch(NumberFormatException ex) {
                    logger.error("Error in the format of the data: "+ex.getMessage());
                    return null;
                }
                catch(Exception ex){
                    logger.error("Error in the data conversion: "+ex.getMessage());
                    return null;
                }
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

    public static WeeklyIndex convertCsvRecordToWeeklyIndex(CSVRecord csvRecord){
        return new WeeklyIndex(Integer.parseInt(csvRecord.get("quarter")),
                csvRecord.get("stock") != null ? csvRecord.get("stock") : "",
                csvRecord.get("date") != null ? csvRecord.get("date") : "",
                new BigDecimal(removeDollar(csvRecord.get("open"))),
                new BigDecimal(removeDollar(csvRecord.get("high"))),
                new BigDecimal(removeDollar(csvRecord.get("low"))),
                new BigDecimal(removeDollar(csvRecord.get("close"))),
                new Long(csvRecord.get("volume")),
                Double.parseDouble(csvRecord.get("percent_change_price")),
                csvRecord.get("percent_change_volume_over_last_wk")!= null ? csvRecord.get("percent_change_volume_over_last_wk"):"",
                csvRecord.get("previous_weeks_volume")!= null ?csvRecord.get("previous_weeks_volume") : "",
                new BigDecimal(removeDollar(csvRecord.get("next_weeks_open"))),
                new BigDecimal(removeDollar(csvRecord.get("next_weeks_close"))),
                Double.parseDouble(csvRecord.get("percent_change_next_weeks_price")),
                Integer.parseInt(csvRecord.get("days_to_next_dividend")),
                Double.parseDouble(csvRecord.get("percent_return_next_dividend")));
    }

    public static String removeDollar(String value){
        String dollarRemoved = value.replace("$", "");
        return dollarRemoved;
    }

    //TODO: Future functionality to add all error records in new table
    public void createErrorRecord(){ }

}
