package org.example.service;

import org.example.model.Task;
import org.example.model.TaskFilter;
import org.example.model.TaskSortBy;
import org.example.model.TaskTag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TaskManager {
    Task addTask(String taskName, LocalDateTime deadline, LocalDateTime startTime, Set<TaskTag> taskTags, String description);
    Task getTask(String taskId);
    Task modifyTask(String newTaskName, LocalDateTime newDeadline,LocalDateTime startTime, Set<TaskTag> newTaskTags, String newDescription, String taskId);
    boolean removeTask(String taskId);
    boolean completeTask(String taskId);
    List<Task> listTasks(TaskFilter filter, TaskSortBy taskSortBy);

    void getActivityLog(LocalDateTime startTime, LocalDateTime endTime);


}
