package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class Task {
    String id;
    String description;
    Set<TaskTag> taskTags;
    String name;
    boolean isCompleted;
    LocalDateTime deadline;
    LocalDateTime appearanceStartTime;
}
