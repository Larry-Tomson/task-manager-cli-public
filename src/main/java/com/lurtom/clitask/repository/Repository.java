package com.lurtom.clitask.repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import com.lurtom.clitask.model.Status;
import com.lurtom.clitask.model.Task;
=======
import com.lurtom.clitask.model.Status;
import com.lurtom.clitask.model.Task;
import java.util.List;
import java.util.Optional;
>>>>>>> main

public interface Repository {

    public Task add(String description);

<<<<<<< HEAD
    public Optional<Task> update(int id, String description);

=======
>>>>>>> main
    public Optional<Task> delete(int id);

    public List<Task> list();

    public Optional<Task> mark(int id, Status stt);

<<<<<<< HEAD
    public void write();
}
=======
    public Optional<Task> update(int id, String description);

    public void write();
}
>>>>>>> main
