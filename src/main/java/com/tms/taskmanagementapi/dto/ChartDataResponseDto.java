package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Chart Data",description = "Chart Data holder")
public class ChartDataResponseDto {

    @Schema(description = "Labels for chart", example = "[\n" +
            "        \"04-05-2024\",\n" +
            "        \"08-05-2024\",\n" +
            "        \"09-05-2024\",\n" +
            "        \"10-05-2024\",\n" +
            "        \"11-05-2024\",\n" +
            "        \"12-05-2024\"\n" +
            "    ],")
    private Collection<String> labels;


    @Schema(description = "Data for chart", example = "[\n" +
            "        2,\n" +
            "        2,\n" +
            "        1,\n" +
            "        2,\n" +
            "        3,\n" +
            "        4\n" +
            "    ]")
    private Collection<Long> data;

}
