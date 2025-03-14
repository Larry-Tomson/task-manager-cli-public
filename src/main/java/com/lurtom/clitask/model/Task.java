package com.lurtom.clitask.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalInt;

public class Task {
    private static int nextId = 1;
    private LocalDateTime createdTime;
    private String description;
    private int id;
    private Status status;
    private LocalDateTime updatedTime;

    public Task() {
    }

    public Task(String description, Status status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = nextId;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        nextId++;
    }

    public static void setNextId(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            final OptionalInt currentId = tasks.stream().mapToInt(Task::getId).max();
            nextId = currentId.getAsInt() + 1;
        } else {
            nextId = 1;
        }
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String toJson() {
        return "{" + "\"id\":\"" + id + "\"" + ", \"description\":\"" + description + "\"" + ", \"status\":\"" + status
                + "\"" + ", \"createdTime\":\"" + createdTime + "\"" + ", \"updatedTime\":\"" + updatedTime
                + "\"" + "}";
    }

    @Override
    public String toString() {
        return "Task:" + "id=" + id + ", description='" + description + '\'' + ", status='" + status + '\''
                + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime;
    }
}
