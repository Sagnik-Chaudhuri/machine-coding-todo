package org.example.service;


import org.example.model.Task;
import org.example.model.TaskFilter;
import org.example.model.TaskSortBy;
import org.example.model.TaskTag;
import org.example.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class TaskManagerImpl implements TaskManager {

    Repository repository;

    public TaskManagerImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Task addTask(String taskName, LocalDateTime deadline, LocalDateTime startTime, Set<TaskTag> taskTags, String description) {
        Task newTask = Task.builder()
                .taskTags(taskTags)
                .isCompleted(false)
                .deadline(deadline)
                .name(taskName)
                .description(description)
                .appearanceStartTime(startTime)
                .build();
        return repository.addTask(newTask);
    }

    @Override
    public Task getTask(String taskId) {
        return repository.getTask(taskId);
    }

    @Override
    public Task modifyTask(String newTaskName, LocalDateTime newDeadline, LocalDateTime startTime, Set<TaskTag>newTaskTags, String newDescription, String taskId) {
        Task newTask = Task.builder()
                .taskTags(newTaskTags)
                .isCompleted(false)
                .deadline(newDeadline)
                .appearanceStartTime(startTime)
                .description(newDescription)
                .name(newTaskName)
                .build();
        return repository.modifyTask(newTask, taskId);
    }

    @Override
    public boolean removeTask(String taskId) {
        return repository.removeTask(taskId);
    }

    @Override
    public boolean completeTask(String taskId) {
        return repository.completeTask(taskId);
    }

    @Override
    public List<Task> listTasks(TaskFilter filter, TaskSortBy taskSortBy) {
        return repository.listTasks(filter, taskSortBy);
    }

    @Override
    public void getActivityLog(LocalDateTime startTime, LocalDateTime endTime) {
        repository.getActivityLog(startTime, endTime);
    }


}
