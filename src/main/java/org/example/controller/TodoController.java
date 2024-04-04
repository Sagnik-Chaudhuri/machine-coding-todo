package org.example.controller;

import org.example.model.Task;
import org.example.model.TaskFilter;
import org.example.model.TaskSortBy;
import org.example.model.TaskTag;
import org.example.service.TaskManager;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class TodoController {
    TaskManager taskManager;

    public TodoController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Task addTask(String taskName, LocalDateTime deadline, LocalDateTime startTime, List<TaskTag> taskTags, String description){
        // basic validations
        if (Objects.isNull(taskTags) || taskTags.isEmpty()) {
            System.out.println("taskTags cannot be empty");
            return null;
        }
        if (Objects.isNull(description) || description.isEmpty()) {
            System.out.println("description cannot be empty");
            return null;
        }
        if (Objects.isNull(deadline)) {
            System.out.println("deadline cannot be empty");
            return null;
        }
        try {
            return taskManager.addTask(taskName, deadline, startTime, new HashSet<>(taskTags), description);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Task getTask(String taskId) {
        if (Objects.isNull(taskId) || taskId.isEmpty()) {
            System.out.println("taskId cannot be empty");
            return null;
        }
        try {
            return taskManager.getTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Task modifyTask(String newTaskName, LocalDateTime newDeadline, LocalDateTime startTime, List<TaskTag> newTaskTags, String newDescription, String taskId) {
        // basic validations
        if (Objects.isNull(newTaskTags) || newTaskTags.isEmpty()) {
            System.out.println("newTaskTags cannot be empty");
            return null;
        }
        if (Objects.isNull(newDescription) || newDescription.isEmpty()) {
            System.out.println("newDescription cannot be empty");
            return null;
        }
        if (Objects.isNull(newDeadline)) {
            System.out.println("newDeadline cannot be empty");
            return null;
        }
        try {
            return taskManager.modifyTask(newTaskName, newDeadline, startTime, new HashSet<>(newTaskTags), newDescription, taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean removeTask(String taskId) {
        if (Objects.isNull(taskId) || taskId.isEmpty()) {
            System.out.println("taskId cannot be empty");
            return false;
        }
        try {
            return taskManager.removeTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean completeTask(String taskId) {
        if (Objects.isNull(taskId) || taskId.isEmpty()) {
            System.out.println("taskId cannot be empty");
            return false;
        }
        try {
            return taskManager.completeTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Task> listTasks(TaskFilter filter, TaskSortBy taskSortBy) {
        if (Objects.isNull(filter)) {
            System.out.println("filter cannot be empty");
            return null;
        }
        if (Objects.isNull(taskSortBy)) {
            System.out.println("taskSortBy cannot be empty");
            return null;
        }
        try {
            return taskManager.listTasks(filter, taskSortBy);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getActivityLog(LocalDateTime startTime, LocalDateTime endTime) {
        taskManager.getActivityLog(startTime, endTime);
    }


}
