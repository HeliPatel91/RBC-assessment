package com.rbc.dow.jones.index.controller;

import com.rbc.dow.jones.index.exception.RecordAlreadyExists;
import com.rbc.dow.jones.index.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UploadControllerTest {

    @Mock
    private IndexService indexService;

    @InjectMocks
    private UploadController controller;

    @Test
    public void uploadData_uploadedSuccessfully() throws RecordAlreadyExists
    {

        MockMultipartFile csvFile = new MockMultipartFile("data", "filename.csv", "text/csv", "some csv".getBytes());

        ResponseEntity<String> response =controller.uploadData(csvFile);
        verify(indexService).uploadData(csvFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File uploaded successfully: filename.csv", response.getBody());
    }

    @Test
    public void uploadData_wrongFormat() {
        MockMultipartFile jsonFile = new MockMultipartFile("data", "filename.json", "application/json", "some json".getBytes());
        ResponseEntity<String> response = null;

        response = controller.uploadData(jsonFile);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        assertEquals("Please upload CSV type file!", response.getBody());

    }
}
