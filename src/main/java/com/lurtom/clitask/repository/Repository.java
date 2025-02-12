package com.lurtom.clitask.repository;

import java.util.List;
import java.util.Optional;

import com.lurtom.clitask.model.Status;
import com.lurtom.clitask.model.Task;

public interface Repository {

    public Task add(String description);

    public Optional<Task> update(int id, String description);

    public Optional<Task> delete(int id);

    public List<Task> list();

    public Optional<Task> mark(int id, Status stt);

    public void write();
}