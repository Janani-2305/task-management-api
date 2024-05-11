package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.ChartData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chart")
public class ChartController {

    @GetMapping("/get-chart-data")
    public ResponseEntity<ChartData> getChartData(){
        return null;
    }

}
