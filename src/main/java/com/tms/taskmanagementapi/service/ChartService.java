package com.tms.taskmanagementapi.service;

import com.tms.taskmanagementapi.dto.ChartData;
import com.tms.taskmanagementapi.dto.ChartDataResponseDto;
import com.tms.taskmanagementapi.dto.User;
import com.tms.taskmanagementapi.entity.Task;
import com.tms.taskmanagementapi.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChartService {

    @Autowired
    private TaskRepository taskRepository;

    public ChartDataResponseDto getChartData(){

        Map<String, Long> map = taskRepository.findAllByUserId(User.userId)
                .stream()
                .filter(task -> task.isCompleted())
                .sorted(Comparator.comparing(Task::getCompletedOn).reversed())
                .limit(15)
                .map(ChartService::mapToChartData)
                .collect(Collectors.groupingBy(ChartData::getLabel, LinkedHashMap::new, Collectors.counting()));

        return new ChartDataResponseDto(map.keySet(), map.values());
    }

    private static ChartData mapToChartData(Task task){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String label = task.getCompletedOn().format(formatter);
        log.info(label);

        return new ChartData(label, 1);
    }

}
