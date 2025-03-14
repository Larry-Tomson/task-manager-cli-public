package com.lurtom.clitask.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalInt;

public class Task {
    private static int nextId = 1;

<<<<<<< HEAD
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

=======
    public static void main(String[] args) {}

    public static void setNextId(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            OptionalInt currentId = tasks.stream().mapToInt(Task::getId).max();
            nextId = currentId.getAsInt() + 1;
        } else {
            nextId = 1;
        }
    }

    private LocalDateTime createdTime;
    private String description;
    private int id;

    private Status status;

    private LocalDateTime updatedTime;

    public Task() {}

>>>>>>> main
    public Task(String description, Status status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = nextId;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        nextId++;
    }

<<<<<<< HEAD
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
=======
    public LocalDateTime getCreatedTime() {
        return createdTime;
>>>>>>> main
    }

    public String getDescription() {
        return description;
    }

<<<<<<< HEAD
    public void setDescription(String description) {
        this.description = description;
=======
    public int getId() {
        return id;
>>>>>>> main
    }

    public Status getStatus() {
        return status;
    }

<<<<<<< HEAD
    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
=======
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
>>>>>>> main
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

<<<<<<< HEAD
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
=======
    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
>>>>>>> main
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Task:" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime;
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

=======
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
>>>>>>> main
}
