package com.tms.taskmanagementapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task extends BaseEntity implements Comparable<Task>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer priority;
    private boolean completed;
    private String status;
    private LocalDateTime completedOn;
    private Date targetDate;
    private Long userId;

    @Override
    public int compareTo(Task task) {

        if(this.priority < task.getPriority()){
            return -1;
        }else if( this.priority > task.getPriority()){
            return 1;
        }else{
            return 0;
        }
    }
}
