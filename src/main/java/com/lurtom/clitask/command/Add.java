package com.lurtom.clitask.command;

import com.lurtom.clitask.repository.*;
import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.*;
import com.lurtom.clitask.util.*;

public class Add extends BaseCommand implements Command {
    private final Logger logger = new Logger();
    private static final int ARGS_COUNT = 2;

    public Add(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, ARGS_COUNT);
    }

    public void execute(String[] args) {
        String description = args[1];
        String inputNullErr = confLoader.getValue("add.error.input.null");
        String taskInfoFormat = confLoader.getValue("add.format");
        logger.info("Adding description \"{}\" as new task", description);

        if (description == null || description.isEmpty()) {
            logger.info("description is null or empty");
            CLIRenderer.error(inputNullErr);
            return;
        }

        Task task = repository.add(description);
        logger.info("Added new task with task id={} description={} status={}",
                task.getId(),
                task.getDescription(),
                task.getStatus() //
        );
        CLIRenderer.message(String.format(
                CLIColor.GREEN + taskInfoFormat + CLIColor.RESET,
                task.getId(),
                task.getDescription().length() < 49 ? task.getDescription()
                        : task.getDescription().substring(0, 49) + " ...",
                task.getStatus().getValueStr(),
                CLIRenderer.formatTime(task.getCreatedTime(), TimeFormat.LONG),
                CLIRenderer.formatTime(task.getUpdatedTime(), TimeFormat.LONG)));
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("add.helpMessage");
    }
}