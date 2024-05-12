package com.tms.taskmanagementapi.repository;

import com.tms.taskmanagementapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserIdAndNameContaining(Long userId, String searchString);

    List<Task> findAllByUserId(Long userId);

}
