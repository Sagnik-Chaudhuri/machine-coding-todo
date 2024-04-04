package org.example.service;

import org.example.model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeadlineSortProvider implements SortProvider{
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline))
                .collect(Collectors.toList());
    }
}
