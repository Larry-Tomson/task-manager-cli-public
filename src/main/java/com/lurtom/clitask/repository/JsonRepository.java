package com.lurtom.clitask.repository;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.*;
import com.lurtom.clitask.util.JsonParser;
<<<<<<< HEAD

import java.io.IOException;
import java.nio.charset.StandardCharsets;
=======
import java.io.IOException;
>>>>>>> main
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> main
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonRepository implements Repository {
<<<<<<< HEAD
    private static final String DEFAULT_JSON_PATH = "./json";
    private static final String DEFAULT_DATABASE_FILE_NAME = "database.json";
    private static final Logger logger = new Logger();
    private final JsonParser parser = new JsonParser();
    private List<Task> tasks;
=======
    private static final String DEFAULT_DATABASE_FILE_NAME = "database.json";
    private static final String DEFAULT_JSON_PATH = "./json";
    private static final Logger logger = new Logger();
>>>>>>> main

    private static Path getPath() throws IOException {
        try {
            Path path = Paths.get(DEFAULT_JSON_PATH);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            Path file = path.resolve(DEFAULT_DATABASE_FILE_NAME);

            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            return path.toAbsolutePath();

        } catch (IOException e) {
            logger.error("Failed to create directory", e);
            throw new IOException("Failed to create log directory");
        }
    }

<<<<<<< HEAD
=======
    private final JsonParser parser = new JsonParser();

    private List<Task> tasks;

>>>>>>> main
    public JsonRepository() {
        this.tasks = this.read();
        Task.setNextId(tasks);
        logger.info("Initiating database with {} items", this.tasks.size());
    }

<<<<<<< HEAD
=======
    public Task add(String description) {
        Task task = new Task(description, Status.PENDING, LocalDateTime.now(), LocalDateTime.now());

        logger.info("new Task id={}, description={}, status={}, date={}", task.getId(), task.getDescription(),
                task.getStatus(), task.getCreatedTime() //
        );
        tasks.add(task);
        return task;
    }

    public Optional<Task> delete(int id) {
        Optional<Task> removedTask = tasks.stream().filter(t -> t.getId() == id).findFirst();

        removedTask.ifPresent(task -> {
            logger.info("remove task id: {}", id);
            tasks.remove(task);
        });
        return removedTask;
    }

    public List<Task> list() {
        logger.info("Listing task with {} items", tasks.size());
        return tasks;
    }

    public Optional<Task> mark(int id, Status status) {
        Optional<Task> markTask = tasks.stream().filter(task -> task.getId() == id).findFirst();

        markTask.ifPresent(task -> {
            logger.info("mark task id {}, {} -> {}", id, task.getStatus(), status //
            );
            task.setStatus(status);
            task.setUpdatedTime(LocalDateTime.now());
        });
        return markTask;
    }

>>>>>>> main
    public List<Task> read() {
        List<Task> taskList = new ArrayList<>();
        try {
            Path path = getPath().resolve(DEFAULT_DATABASE_FILE_NAME);
            String input = Files.readString(path);
            if (!input.isEmpty()) {
                taskList = parser.parseString(input);
            }
        } catch (IOException e) {
            logger.error("Unable to read file ", e);
        }
        return taskList;
<<<<<<< HEAD

=======
    }

    public Optional<Task> update(int id, String newDescription) {
        Optional<Task> updateTask = tasks.stream().filter(task -> task.getId() == id).findAny();
        updateTask.ifPresent(task -> {
            logger.info("update task id: {}, des: \"{}\" -> \"{}\", time: {} -> {}", id, task.getDescription(),
                    newDescription, task.getUpdatedTime(), LocalDateTime.now());
            task.setDescription(newDescription);
            task.setUpdatedTime(LocalDateTime.now());
        });
        return updateTask;
>>>>>>> main
    }

    public void write() {

<<<<<<< HEAD
        String jsonOutput = tasks.stream()
                .map(Task::toJson)
                .collect(Collectors.joining(", ", "[", "]"));
        logger.trace("{}", jsonOutput);
        try {
            Path path = getPath().resolve(DEFAULT_DATABASE_FILE_NAME);
            Files.writeString( //
                    path,
                    jsonOutput,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING //
=======
        String jsonOutput = tasks.stream() //
                .map(Task::toJson) //
                .collect(Collectors.joining(", ", "[", "]"));
        try {
            Path path = getPath().resolve(DEFAULT_DATABASE_FILE_NAME);
            Files.writeString( //
                    path, jsonOutput, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING //
>>>>>>> main
            );
        } catch (IOException e) {
            logger.error("unable to write to file", e);
        }
    }
<<<<<<< HEAD

    public Task add(String description) {
        Task task = new Task(description,
                Status.PENDING,
                LocalDateTime.now(),
                LocalDateTime.now());

        logger.info("new Task id={}, description={}, status={}, date={}",
                task.getId(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedTime() //
        );
        tasks.add(task);
        return task;
    }

    public Optional<Task> update(int id, String newDescription) {
        Optional<Task> updateTask = tasks.stream()
                .filter(task -> task.getId() == id)
                .findAny();
        updateTask.ifPresent(task -> {
            LocalDateTime now = LocalDateTime.now();
            logger.info("update task id: {}, des: \"{}\" -> \"{}\", time: {} -> {}",
                    id,
                    task.getDescription(),
                    newDescription,
                    task.getUpdatedTime(), now);
            task.setDescription(newDescription);
            task.setUpdatedTime(now);
        });
        return updateTask;
    }

    public Optional<Task> delete(int id) {
        Optional<Task> removedTask = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        removedTask.ifPresent(task -> {
            logger.info("remove task id: {}", id);
            tasks.remove(task);
        });
        return removedTask;
    }

    public Optional<Task> mark(int id, Status status) {
        Optional<Task> markTask = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();

        markTask.ifPresent(task -> {
            logger.info("mark task id {}, {} -> {}",
                    id,
                    task.getStatus(),
                    status //
            );
            task.setStatus(status);
            task.setUpdatedTime(LocalDateTime.now());
        });
        return markTask;
    }

    public List<Task> list() {
        logger.info("Listing task with {} items", tasks.size());
        return tasks;
    }

=======
>>>>>>> main
}
