package org.example.repository;

import org.example.model.Task;
import org.example.model.TaskFilter;
import org.example.model.TaskSortBy;

import java.time.LocalDateTime;
import java.util.List;
//import org.example.model.User;

public interface Repository {
    Task addTask(Task task);
    Task getTask(String taskId);
    Task modifyTask(Task task, String existingTaskId);
    boolean removeTask(String taskId);

    boolean completeTask(String taskId);
    List<Task> listTasks(TaskFilter filter, TaskSortBy taskSortBy);

    void getActivityLog(LocalDateTime startTime, LocalDateTime endTime);
}
