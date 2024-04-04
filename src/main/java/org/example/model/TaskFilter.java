package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskFilter {
    TaskTag taskTag;
    boolean taskCompletenessFilter;
}
