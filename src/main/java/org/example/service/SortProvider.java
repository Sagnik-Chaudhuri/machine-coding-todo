package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface SortProvider {
    List<Task> sort(List<Task> tasks);
}
