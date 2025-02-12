package com.lurtom.clitask.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalInt;

public class Task {
    private static int nextId = 1;

    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Task(String description, Status status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = nextId;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        nextId++;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    static public void setNextId(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            OptionalInt currentId = tasks.stream()
                    .mapToInt(Task::getId)
                    .max();
            nextId = currentId.getAsInt() + 1;
        } else {
            nextId = 1;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Task:" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime;
    }

    public static void main(String[] args) {

    }

    public String toJson() {
        return "{" +
                "\"id\":\"" + id + "\"" +
                ", \"description\":\"" + description + "\"" +
                ", \"status\":\"" + status + "\"" +
                ", \"createdTime\":\"" + createdTime + "\"" +
                ", \"updatedTime\":\"" + updatedTime + "\"" +
                "}";
    }

}
