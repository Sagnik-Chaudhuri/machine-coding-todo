package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskActivity {
    String taskId;
    ActivityType activity;
    LocalDateTime activityLogTime;
}
