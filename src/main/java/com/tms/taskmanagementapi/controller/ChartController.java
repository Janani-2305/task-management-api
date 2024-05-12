package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.ChartData;
import com.tms.taskmanagementapi.dto.ChartDataResponseDto;
import com.tms.taskmanagementapi.service.ChartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Chart Data APIs",
        description = "This helps to get the data for Chart"
)
@RestController
@RequestMapping("/api/v1/chart")
@CrossOrigin("*")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Operation(
            summary = "Get Chart Data",
            description = "This API will helps to get the chart data in key and value format"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @GetMapping("/get-chart-data")
    public ResponseEntity<ChartDataResponseDto> getChartData(){
        return new ResponseEntity(chartService.getChartData(), HttpStatus.OK);
    }

}
