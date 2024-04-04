package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import org.example.controller.TodoController;
import org.example.model.Task;
import org.example.model.TaskFilter;
import org.example.model.TaskSortBy;
import org.example.model.TaskTag;
import org.example.repository.Repository;
import org.example.repository.RepositoryImpl;
import org.example.service.TaskManager;
import org.example.service.TaskManagerImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Repository repository = new RepositoryImpl();
        TaskManager taskManager = new TaskManagerImpl(repository);
        TodoController controller = new TodoController(taskManager);

        Task task1 = controller
                .addTask("task1", LocalDateTime.of(2024, 3, 21, 10, 30, 0),
                        LocalDateTime.now().plusSeconds(3), Arrays.asList(TaskTag.OUTDOOR_ACTIVITY), "task1");
        Task task2 = controller
                .addTask("task2", LocalDateTime.of(2024, 6, 30, 10, 30, 0),
                        LocalDateTime.now().plusSeconds(3), Arrays.asList(TaskTag.INDOOR_ACTIVITY), "task2");
        Task task4 = controller
                .addTask("task4", LocalDateTime.of(2024, 3, 30, 10, 30, 0),
                        LocalDateTime.now(), Arrays.asList(TaskTag.OUTDOOR_ACTIVITY), "task4");

//        Task task3 = controller
//                .modifyTask("task3", LocalDateTime.of(2024, 6, 30, 10, 30, 0),
//                        LocalDateTime.now(), Arrays.asList(TaskTag.OUTDOOR_ACTIVITY), "task3", task1.getId());

//        System.out.println(task3);
        System.out.println(task1);
//        task3 = controller.getTask(task2.getId());
//        System.out.println(task3);

        System.out.println(controller.completeTask(task1.getId()));
        System.out.println(controller.getTask(task1.getId()));

        controller.completeTask(task4.getId());

        TaskFilter taskFilter = TaskFilter.builder()
                .taskTag(TaskTag.OUTDOOR_ACTIVITY)
                .taskCompletenessFilter(true)
                .build();
        System.out.println("list after 0 seconds: " + controller.listTasks(taskFilter, TaskSortBy.DEADLINE));

        Thread.sleep(3000);
        controller.getActivityLog(LocalDateTime.now().minusSeconds(10), LocalDateTime.now());

        Thread.sleep(3000);
        System.out.println("list after 3 seconds: " + controller.listTasks(taskFilter, TaskSortBy.DEADLINE));

    }
}