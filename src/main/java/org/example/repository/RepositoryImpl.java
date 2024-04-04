package org.example.repository;

import org.example.exception.UserException;
import org.example.model.*;
import org.example.service.DeadlineSortProvider;
import org.example.service.NameSortProvider;
import org.example.service.SortProvider;

import java.time.LocalDateTime;
import java.util.*;

public class RepositoryImpl implements  Repository{
    Map<String, Task> taskMap = new HashMap<>();
    List<String> todoTaskIdsList = new ArrayList<>();
    List<TaskActivity> taskActivitiesLog = new ArrayList<>();

    @Override
    public Task addTask(Task task) {
        String taskId = UUID.randomUUID().toString();
        task.setId(taskId);
        taskMap.put(taskId, task);
        todoTaskIdsList.add(taskId);
        TaskActivity taskActivity = TaskActivity.builder()
                .taskId(taskId)
                .activity(ActivityType.ADD)
                .activityLogTime(LocalDateTime.now())
                .build();
        taskActivitiesLog.add(taskActivity);
        return task;
    }

    @Override
    public Task getTask(String taskId) {
        Task task = taskMap.getOrDefault(taskId, null);
        if (Objects.isNull(task)) {
            System.out.println("Task id not found: " + taskId);
            throw new UserException("Task id not found: " + taskId);
        }
        return task;
    }

    @Override
    public Task modifyTask(Task newTask, String existingTaskId) {
        Task getExistingTask = getTask(existingTaskId);
        if (Objects.isNull(getExistingTask)) {
            System.out.println("Task to update is not found: " + newTask.getId());
            throw new UserException("Task id not found: " + newTask.getId());
        }
        getExistingTask.setTaskTags(newTask.getTaskTags());
        getExistingTask.setName(newTask.getName());
        getExistingTask.setDeadline(newTask.getDeadline());
        getExistingTask.setCompleted(newTask.isCompleted());
        getExistingTask.setDescription(newTask.getDescription());

        TaskActivity taskActivity = TaskActivity.builder()
                .taskId(newTask.getId())
                .activity(ActivityType.MODIFY)
                .activityLogTime(LocalDateTime.now())
                .build();
        taskActivitiesLog.add(taskActivity);
        newTask.setId(existingTaskId);
        taskMap.put(existingTaskId, getExistingTask);
        return newTask;
    }

    @Override
    public boolean removeTask(String taskId) {
        Task getExistingTask = getTask(taskId);
        if (Objects.isNull(getExistingTask)) {
            System.out.println("Task to remove is not found: " + taskId);
            throw new UserException("Task id not found: " + taskId);
        }
        TaskActivity taskActivity = TaskActivity.builder()
                .taskId(taskId)
                .activity(ActivityType.REMOVE)
                .activityLogTime(LocalDateTime.now())
                .build();
        taskActivitiesLog.add(taskActivity);
        taskMap.remove(taskId);

        for (String todoTaskId : todoTaskIdsList) {
            if (todoTaskId.equals(taskId)) {
                todoTaskIdsList.remove(taskId);
            }
        }
        return true;
    }

    @Override
    public boolean completeTask(String taskId) {
        Task getExistingTask = getTask(taskId);
        if (Objects.isNull(getExistingTask)) {
            System.out.println("Task to remove is not found: " + taskId);
            throw new UserException("Task id not found: " + taskId);
        }

        getExistingTask.setCompleted(true);
        String taskIdToRemove = "";
        for (String todoTaskId : todoTaskIdsList) {
            if (todoTaskId.equals(taskId)) {
                taskIdToRemove = taskId;
            }
        }
        TaskActivity taskActivity = TaskActivity.builder()
                .taskId(taskId)
                .activity(ActivityType.COMPLETED)
                .activityLogTime(LocalDateTime.now())
                .build();
        taskActivitiesLog.add(taskActivity);
        todoTaskIdsList.remove(taskIdToRemove);
        return true;
    }

    @Override
    public List<Task> listTasks(TaskFilter filter, TaskSortBy taskSortBy) {
        List<Task> filteredTasks = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now();
        for (Task task : taskMap.values()) {
            // check filter and add current time filter as well
            if (filterMatches(task, filter) && currentTime.isAfter(task.getAppearanceStartTime())) {
                filteredTasks.add(task);
            }
        }
        SortProvider sortProvider = getSortProvider(taskSortBy);
        return sortProvider.sort(filteredTasks);
    }

    @Override
    public void getActivityLog(LocalDateTime startTime, LocalDateTime endTime) {
        if (Objects.isNull(startTime) && Objects.isNull(endTime)) {
            for (TaskActivity taskActivity : taskActivitiesLog) {
                System.out.println("Activity Log: " + taskActivity);
            }
        } else {
            for (TaskActivity taskActivity : taskActivitiesLog) {
                if (taskActivity.getActivityLogTime().isBefore(endTime) && taskActivity.getActivityLogTime().isAfter(startTime)) {
                    System.out.println("Activity Log: " + taskActivity);
                }
            }
        }

    }

    private boolean filterMatches(Task task, TaskFilter taskFilter) {
        if (task.isCompleted() != taskFilter.isTaskCompletenessFilter()) {
            return false;
        }
        if (!task.getTaskTags().contains(taskFilter.getTaskTag())) {
            return false;
        }
        return true;
    }

    private SortProvider getSortProvider(TaskSortBy sortBy) {
        switch (sortBy) {
            case NAME:
                return new NameSortProvider();
            case DEADLINE:
                return new DeadlineSortProvider();
            default:
                throw new UserException("Sort provider not found");
        }

    }
}
