package com.app.demopdf.controller;

import com.app.demopdf.entity.City;
import com.app.demopdf.helper.GeneratePdfReport;
import com.app.demopdf.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/generate/pdf")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> cityReport() throws IOException{

        List<City> cities = cityService.findAll();
        ByteArrayInputStream byteInputStream = GeneratePdfReport.cityReport(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity.ok()
                .header(String.valueOf(headers))
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteInputStream));
    }
}
