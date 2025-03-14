package com.lurtom.clitask.command;

import com.lurtom.clitask.logger.Logger;
import com.lurtom.clitask.model.*;
import com.lurtom.clitask.repository.*;
import com.lurtom.clitask.util.*;

public class Add extends BaseCommand implements Command {
    private static final int ARGS_COUNT = 2;
    private final Logger logger = new Logger();

    public Add(Repository repository, ConfigurationLoader confLoader) {
        super(repository, confLoader, ARGS_COUNT);
    }

    public void execute(String[] args) {
        final String description = args[1];
        final String inputNullErr = confLoader.getValue("add.error.input.null");
        final String taskInfoFormat = confLoader.getValue("add.format");
        logger.info("Adding description \"{}\" as new task", description);

        if (description == null || description.isEmpty()) {
            logger.info("description is null or empty");
            CLIRenderer.error(inputNullErr);
            return;
        }

        final Task task = repository.add(description);
        final String taskDescription = task.getDescription();
        final String coloredFormat = CLIColor.GREEN + taskInfoFormat + CLIColor.RESET;

        logger.info("Added new task with task id={} description={} status={}", task.getId(), taskDescription,
                task.getStatus() //
        );

        CLIRenderer.message(String.format(coloredFormat, task.getId(), //
                getTruncatedDescription(taskDescription), //
                task.getStatus().getValueStr(), //
                CLIRenderer.formatTime(task.getCreatedTime(), TimeFormat.LONG), //
                CLIRenderer.formatTime(task.getUpdatedTime(), TimeFormat.LONG)));
    }

    @Override
    public String getHelp() {
        return confLoader.getValue("add.helpMessage");
    }

    private String getTruncatedDescription(String taskDescription) {
        return taskDescription.length() < 49 ? //
                taskDescription : taskDescription.substring(0, 49) + " ...";
    }
}
